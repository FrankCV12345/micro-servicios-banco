package com.formacionbdi.microservicios.app.cliente.models.service;

import java.util.List;

public interface TodoService {
	public List<String> retriveTodos(String user);
}
