package com.formacionbdi.microservicios.app.cliente.models.service;

import java.util.List;

import com.formacionbdi.microservicios.common.entity.Producto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ICuentasService {
	@GET("cuenta/{id}")
	Call<List<Producto>> cuentasDeCliente(@Path("id")  Long idCliente); 
	
	@POST("cuenta/")
	Call<Producto> crearCuenta(@Body Producto producto);
	
	@PUT("cuenta/{id}")
	Call<Producto> actualizaProducto(@Body Producto producto,@Path("id") Long id);
	
	
}
