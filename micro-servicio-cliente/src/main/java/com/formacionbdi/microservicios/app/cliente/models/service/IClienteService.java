package com.formacionbdi.microservicios.app.cliente.models.service;

import java.util.List;

import com.formacionbdi.microservicios.common.entity.Cliente;
import com.formacionbdi.microservicios.common.entity.Producto;

import io.reactivex.Maybe;
import io.reactivex.Single;

public interface IClienteService {
	public Single<Cliente> save(Cliente cliente);
	public Maybe<Cliente> findById(Long id);
	public Single<List<Producto>> getProductFindbyIdClient(Long idCliente);
}
