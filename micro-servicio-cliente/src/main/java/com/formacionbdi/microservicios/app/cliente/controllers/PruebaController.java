package com.formacionbdi.microservicios.app.cliente.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
@RestController
@RequestMapping("/personas")
public class PruebaController {

	@GetMapping
	public Single<ResponseEntity<List<Persona>>> pruebita(){
		
		return this.getPersonas()
				.subscribeOn(Schedulers.io())
				.map( p -> ResponseEntity.ok(p));
				
	}
	
	private Single<List<Persona>> getPersonas(){
		
		List<Persona> personas = new ArrayList<>();
		personas.add(new Persona(1,"jose"));
		personas.add(new Persona(3,"pepe"));
		
		return Single.create( ( p ) -> {
			p.onSuccess(personas);
			p.onError(null);
		} );
	}
	
}

class Persona {
	private Integer id;
	private String  name;
	
	
	public Persona(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
