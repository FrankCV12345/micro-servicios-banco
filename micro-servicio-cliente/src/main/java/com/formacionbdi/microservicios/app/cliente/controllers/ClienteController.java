package com.formacionbdi.microservicios.app.cliente.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.microservicios.app.cliente.models.service.ClienteServiceImple;
import com.formacionbdi.microservicios.app.cliente.requestModels.RequestNewClien;
import com.formacionbdi.microservicios.app.cliente.requestModels.ResponseError;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	ClienteServiceImple clienteService;
	@Autowired
	ResponseError responseError;
	
	@GetMapping("/{id}")
	public Maybe<ResponseEntity<Object>> buscarCliente(@PathVariable Long id){
		return clienteService.findById(id)
				.map(c -> ResponseEntity.status(HttpStatus.OK).body(c))
				.onErrorReturn( e -> responseError.responseEntityOnError(e));
	}
	
	@GetMapping("/{id}/productos")
	public Single<ResponseEntity<Object>> buscarProductosPorCliente(@PathVariable Long id){
		return clienteService.getProductFindbyIdClient(id)
				.subscribeOn(Schedulers.io())
				.map( p -> ResponseEntity.status(HttpStatus.OK).body(p))
				.onErrorReturn(e -> responseError.responseEntityOnError(e));
	}
	
	@PostMapping
	public Single<ResponseEntity<Object>> crearCliente(@RequestBody RequestNewClien cliente){
		return clienteService.save(cliente)
				.subscribeOn(Schedulers.io())
				.map( c ->  ResponseEntity.status(HttpStatus.CREATED).body(c))
				.onErrorReturn( e -> responseError.responseEntityOnError(e));
	}
	
	
}
