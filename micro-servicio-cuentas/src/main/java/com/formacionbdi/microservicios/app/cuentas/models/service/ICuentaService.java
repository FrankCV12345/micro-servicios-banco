package com.formacionbdi.microservicios.app.cuentas.models.service;

import java.util.List;

import com.formacionbdi.microservicios.common.entity.Producto;

import io.reactivex.Single;

public interface ICuentaService {
	public Single<Object> crear(Producto produto);
	public Single<List<Producto>> ListarProductoPorIdCliente(Long id);
	public Single<Double> retiro(Transacion t);
	public Single<Double> deposito(Transacion t);
}
