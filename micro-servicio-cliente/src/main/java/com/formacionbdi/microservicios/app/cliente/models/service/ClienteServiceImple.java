package com.formacionbdi.microservicios.app.cliente.models.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionbdi.microservicios.app.cliente.repository.IRepositoryCliente;
import com.formacionbdi.microservicios.common.entity.Cliente;
import com.formacionbdi.microservicios.common.entity.Producto;

import io.reactivex.Maybe;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
@Service
public class ClienteServiceImple implements IClienteService {

	@Autowired
	private IRepositoryCliente clienteRepostory;
	Retrofit retrofit = new Retrofit.Builder()
			.baseUrl("http://localhost:8088/")
			.addConverterFactory(GsonConverterFactory.create())
			.build();
	ICuentasService cuentaService = retrofit.create(ICuentasService.class);
	
	@Override
	public Single<Cliente> save(Cliente cliente) {
		// TODO Auto-generated method stub
		return Single.create( s -> {
				Cliente clientenew = clienteRepostory.save(cliente);
				s.onSuccess(clientenew);
		});
	}

	@Override
	public Maybe<Cliente> findById(Long id) {
		
		return Maybe.create((s) ->{
			Optional<Cliente> c = clienteRepostory.findById(id);
			if(!c.isPresent()) {
				s.onComplete();
			}else {
				s.onSuccess(c.get());
			}
		});
	}

	@Override
	public Single<List<Producto>> getProductFindbyIdClient(Long idCliente) {
		// TODO Auto-generated method stub
		return Single.create( s -> {
			Call<List<Producto>> call = cuentaService.cuentasDeCliente(idCliente);
			call.enqueue(new Callback<List<Producto>>() {
				@Override
				public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
					// TODO Auto-generated method stub
					if(!response.isSuccessful()) {
						System.out.println("ERROR : "+response.code());
					}else {
						s.onSuccess( response.body());
						//productos.forEach( p  -> System.out.println("Id : "+p.getId()));
					}
				}
				@Override
				public void onFailure(Call<List<Producto>> call, Throwable t) {
					// TODO Auto-generated method stub
					System.out.println("ERROR !!!!"+ t.getMessage());
				}
			});
		});
	}
	
}
