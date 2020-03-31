package com.formacionbdi.microservicios.app.cuentas.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

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
import com.formacionbdi.microservicios.app.cuentas.models.service.SaldoException;
import com.formacionbdi.microservicios.app.cuentas.models.service.Transacion;
import com.formacionbdi.microservicios.common.entity.Producto;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

	@Autowired
	CuentaServiceImplements serviceCuenta;
	
	@PostMapping
	public Single<ResponseEntity<Object>> creaCuenta(@RequestBody Producto pro ){
		return serviceCuenta.crear(pro)
				.map( c ->  ResponseEntity.status(HttpStatus.CREATED).body(c))
				.onErrorReturn( e -> {
					Map<String, Object> rpta = new HashMap<>();
					rpta.put("message", e.getMessage());
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rpta);
				});
	}
	
	@GetMapping("/{id}")
	public Single<ResponseEntity<?>> buscarProductoPorCliente(@PathVariable Long id){
		return	serviceCuenta.ListarProductoPorIdCliente(id)
				.subscribeOn(Schedulers.io())
				.map(c -> 
						{
							if(c instanceof List) {
								return ResponseEntity.status(HttpStatus.OK).body(c);
							}else if(c instanceof SaldoException) {
								return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
							}else if(c instanceof EntityNotFoundException) {
								return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
							}else {
								return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
							}
							//return ResponseEntity.status(HttpStatus.OK).body(c);
							
						} 
					);
	}
	@PostMapping("/retiro")
	public Single<ResponseEntity<?>> retirar(@RequestBody Transacion transaction){
		return serviceCuenta.retiro(transaction)
			.subscribeOn(Schedulers.io())
			.map( c -> ResponseEntity.status(HttpStatus.OK).body(c));
	}
	@PostMapping("/deposito")
	public Single<ResponseEntity<?>> depositar(@RequestBody Transacion transaction){
		return serviceCuenta.deposito(transaction)
			.subscribeOn(Schedulers.io())
			.map( c -> ResponseEntity.status(HttpStatus.OK).body(c));
	}
}
