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
import com.formacionbdi.microservicios.common.entity.Cliente;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	ClienteServiceImple clienteService;
	
	@GetMapping("/{id}")
	public Maybe<ResponseEntity<?>> buscarCliente(@PathVariable Long id){
		return clienteService.findById(id)
				.subscribeOn(Schedulers.io())
				.map(c -> {
					if(c == null) {
						return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
					}else {
						return ResponseEntity.status(HttpStatus.OK).body(c);
						
					}
				});
	}
	
	@GetMapping("/{id}/productos")
	public Single<ResponseEntity<?>> buscarProductosPorCliente(@PathVariable Long id){
		return clienteService.getProductFindbyIdClient(id)
				.subscribeOn(Schedulers.io())
				.map( p -> ResponseEntity.status(HttpStatus.OK).body(p));
	}
	
	@PostMapping
	public Single<ResponseEntity<?>> crearCliente(@RequestBody Cliente cliente){
		return clienteService.save(cliente)
				.subscribeOn(Schedulers.io())
				.map( c ->  ResponseEntity.status(HttpStatus.CREATED).body(c));
	}
	
	
}
