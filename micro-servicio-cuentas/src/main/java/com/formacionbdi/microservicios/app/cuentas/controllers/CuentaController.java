package com.formacionbdi.microservicios.app.cuentas.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.microservicios.app.cuentas.models.service.CuentaServiceImplements;
import com.formacionbdi.microservicios.app.cuentas.models.service.Transacion;
import com.formacionbdi.microservicios.app.cuentas.requestModels.ResponseError;
import com.formacionbdi.microservicios.app.cuentas.requestModels.ResponseSaldo;
import com.formacionbdi.microservicios.common.entity.Producto;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

	@Autowired
	CuentaServiceImplements serviceCuenta;
	@Autowired
	ResponseError responseError;
	@Autowired
	ResponseSaldo responseSaldo;
	
	@PostMapping
	public Single<ResponseEntity<Object>> creaCuenta(@RequestBody Producto pro ){
		return serviceCuenta.crear(pro)
				.map( c ->  ResponseEntity.status(HttpStatus.CREATED).body(c))
				.onErrorReturn( e -> responseError.responseEntityOnError(e));
	}
	
	@GetMapping("/{id}")
	public Single<ResponseEntity<?>> buscarProductoPorCliente(@PathVariable Long id){
		return	serviceCuenta.ListarProductoPorIdCliente(id)
				.subscribeOn(Schedulers.io())
				.map(c -> ResponseEntity.status(HttpStatus.OK).body(c));
	}
	@PostMapping("/retiro")
	public Single<ResponseEntity<Object>> retirar(@RequestBody Transacion transaction){
		return serviceCuenta.retiro(transaction)
			.map( c -> responseSaldo.RespuestaSaldo((Double)c))
			.onErrorReturn(e -> responseError.responseEntityOnError(e));
	}
	@PostMapping("/deposito")
	public Single<ResponseEntity<Object>> depositar(@RequestBody Transacion transaction){
		return serviceCuenta.deposito(transaction)
				.map( c -> responseSaldo.RespuestaSaldo((Double)c))
				.onErrorReturn(e -> responseError.responseEntityOnError(e));
	}
}
