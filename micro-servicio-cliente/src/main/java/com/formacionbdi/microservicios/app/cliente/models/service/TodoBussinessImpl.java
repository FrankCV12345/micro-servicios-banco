package com.formacionbdi.microservicios.app.cliente.models.service;

import java.util.ArrayList;
import java.util.List;

public class TodoBussinessImpl {
	TodoService todoService;

	public TodoBussinessImpl(TodoService todoService) {
		super();
		this.todoService = todoService;
	}
	
	public List<String> retriveTodosRelatedToSpring(String user){
		List<String> filterTodos = new ArrayList<String>();
		List<String> todos = this.todoService.retriveTodos(user);
		todos.stream()
			.filter(s -> s.contains("Spring"))
			.forEach( s -> filterTodos.add(s));
		
		return filterTodos;
	}

}
