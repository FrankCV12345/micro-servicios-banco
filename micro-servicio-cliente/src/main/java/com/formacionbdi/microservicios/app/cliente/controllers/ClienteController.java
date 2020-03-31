package com.formacionbdi.microservicios.app.cliente.controllers;



import java.util.HashMap;
import java.util.Map;


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
	public Maybe<ResponseEntity<Object>> buscarCliente(@PathVariable Long id){
		
		return clienteService.findById(id)
				.map(c -> ResponseEntity.status(HttpStatus.OK).body(c))
				.onErrorReturn( e -> {
					Map<String, Object> rpta = new HashMap<>();
					rpta.put("message", e.getMessage());
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rpta);
				});
	}
	
	@GetMapping("/{id}/productos")
	public Single<ResponseEntity<?>> buscarProductosPorCliente(@PathVariable Long id){
		return clienteService.getProductFindbyIdClient(id)
				.subscribeOn(Schedulers.io())
				.map( p -> ResponseEntity.status(HttpStatus.OK).body(p));
	}
	
	@PostMapping
	public Single<ResponseEntity<Object>> crearCliente(@RequestBody RequestNewClien cliente){
		
		return clienteService.save(cliente)
				.subscribeOn(Schedulers.io())
				.map( c ->  ResponseEntity.status(HttpStatus.CREATED).body(c))
				.onErrorReturn( e -> {
					Map<String, Object> rpta = new HashMap<>();
					rpta.put("message", e.getMessage());
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rpta);
				});
	}
	
	
}
