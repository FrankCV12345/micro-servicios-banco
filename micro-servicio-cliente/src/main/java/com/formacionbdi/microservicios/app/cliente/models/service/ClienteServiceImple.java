package com.formacionbdi.microservicios.app.cliente.models.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.formacionbdi.microservicios.app.cliente.repository.IRepositoryCliente;
import com.formacionbdi.microservicios.app.cliente.requestModels.RequestNewClien;
import com.formacionbdi.microservicios.app.cuentas.excepciones.BadRequestException;
import com.formacionbdi.microservicios.common.entity.Cliente;
import com.formacionbdi.microservicios.common.entity.Producto;

import io.reactivex.Maybe;
import io.reactivex.Single;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Service
//Clase de implementacion de metodo de servicio
public class ClienteServiceImple implements IClienteService {
	private final String urlServicio ="http://localhost:8088/";
	
	@Autowired
	private IRepositoryCliente clienteRepostory;
	
	UtilitarioRetrofit WSCuentas =  new UtilitarioRetrofit(urlServicio);
	
	ICuentasService cuentaService = WSCuentas
			.getRetrofit()
			.create(ICuentasService.class);
	
	
	@Override
	public Single<Object> save(RequestNewClien cliente) {
		return Single.create( s -> {
				Cliente clientenew = clienteRepostory.save(cliente.getCliente());
				
				Producto producto = cliente.getProducto();
				producto.setCliente(new Cliente(clientenew.getId()));
				
				//llamo a mi servicio de cuentas
				Call<Producto> call = cuentaService.crearCuenta(producto);
				
				call.enqueue(new Callback<Producto>() {
					@Override
					public void onResponse(Call<Producto> call, Response<Producto> response) {
						if(response.isSuccessful()) {
							s.onSuccess(clientenew);
						}else {
							String responseBodyOnError;
							try {
								responseBodyOnError = response.errorBody().string();
							} catch (IOException e) {
								responseBodyOnError= "error al formatear json";
							}
							
							
							JSONObject j = new JSONObject(responseBodyOnError);
							//Por mejorar
							if(response.code() == 400) {
								clienteRepostory.delete(clientenew);
								s.tryOnError(new BadRequestException(j.getString("message")));
							}else {
								s.tryOnError(new BadRequestException(j.getString("message")));
							} 
							
						}
					}
					@Override
					public void onFailure(Call<Producto> call, Throwable t) {
						s.tryOnError(new Exception("Error interno"));
					}
				});
		});
	}

	@Override
	public Maybe<Object> findById(Long id) {
		return Maybe.create((s) ->{
			Optional<Cliente> c = clienteRepostory.findById(id);
			if(!c.isPresent()) {
				s.tryOnError(new EntityNotFoundException("Cliente no encontrado"));
			}else {
				s.onSuccess(c.get());
			}
		});
	}
	@Override
	public Single<Object> getProductFindbyIdClient(final Long idCliente) {
		return Single.create( s -> {
			
			final Call<List<Producto>> call = cuentaService.cuentasDeCliente(idCliente);
			
			call.enqueue(new Callback<List<Producto>>() {
				@Override
				public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
					if(!response.isSuccessful()) {
						s.tryOnError(new EntityNotFoundException("Recurso no encontrado"));
					}else {
						s.onSuccess( response.body());
					}
				}
				@Override
				public void onFailure(Call<List<Producto>> call,final Throwable t) {
					s.tryOnError(new Exception("Error generico , fallo la peticion"));
				}
			});
		
		});
	}
	
}
