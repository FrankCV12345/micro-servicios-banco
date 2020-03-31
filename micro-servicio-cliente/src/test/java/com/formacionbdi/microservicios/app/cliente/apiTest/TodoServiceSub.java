package com.formacionbdi.microservicios.app.cliente.apiTest;

import java.util.Arrays;
import java.util.List;

import com.formacionbdi.microservicios.app.cliente.models.service.TodoService;

public class TodoServiceSub implements TodoService {

	@Override
	public List<String> retriveTodos(String user) {
		// TODO Auto-generated method stub
		return Arrays.asList("Learn Spring MVC","Learn Spring Boot","Learn Laravel 7","Learn VueJs");
	}

}
