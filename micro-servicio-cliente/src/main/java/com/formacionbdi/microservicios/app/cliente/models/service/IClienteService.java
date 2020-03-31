package com.formacionbdi.microservicios.app.cliente.models.service;


import com.formacionbdi.microservicios.app.cliente.requestModels.RequestNewClien;
import com.formacionbdi.microservicios.common.entity.Cliente;

import io.reactivex.Maybe;
import io.reactivex.Single;
//interfaz de gobierno de servicio cliente
public interface IClienteService {
	//metodo para guardr cliente ne la BD
	public Single<Object> save(RequestNewClien cliente);
	
	public Maybe<?> findById(Long id);
	public Single<Object> getProductFindbyIdClient(Long idCliente);
}
