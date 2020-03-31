package com.formacionbdi.microservicios.app.cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.formacionbdi.microservicios.app.cliente.apiTest.TodoServiceSub;
import com.formacionbdi.microservicios.app.cliente.models.service.TodoBussinessImpl;
import com.formacionbdi.microservicios.app.cliente.models.service.TodoService;

@SpringBootTest
class MicroServicioClienteApplicationMockitoTests {

	@Test
	void contextLoads() {
		//TodoService todoService = new TodoServiceSub();
		TodoService todoService = mock(TodoService.class);
		List<String> s = Arrays.asList("Learn Spring MVC","Learn Spring Boot","Learn Laravel 7","Learn VueJs");
		when(todoService.retriveTodos("Dummy")).thenReturn(s);
		TodoBussinessImpl todoBussinesService = new TodoBussinessImpl(todoService);
		List<String> filtrador = todoBussinesService.retriveTodosRelatedToSpring("dummy");
		assertEquals(2, filtrador.size());
	}

}
