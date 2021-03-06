package com.formacionbdi.microservicios.app.cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.formacionbdi.microservicios.app.cliente.apiTest.TodoServiceSub;
import com.formacionbdi.microservicios.app.cliente.models.service.TodoBussinessImpl;
import com.formacionbdi.microservicios.app.cliente.models.service.TodoService;
import com.formacionbdi.microservicios.common.entity.Cliente;

import io.reactivex.Maybe;

@SpringBootTest
class MicroServicioClienteApplicationTests {
	@Autowired
	WebTestClient webTestClient;
	@Test
	void contextLoads() {
		TodoService todoService = new TodoServiceSub();
		TodoBussinessImpl todoBussinesService = new TodoBussinessImpl(todoService);
		List<String> filtrador = todoBussinesService.retriveTodosRelatedToSpring("dummy");
		assertEquals(2, filtrador.size());
	}
	
	/*@Test
	void getClientTest() {
		Maybe<Cliente> clienteMaybe = webTestClient
								.get()
								.uri("")
								.accept(MediaType.APPLICATION_JSON)
								.exchange()
								.expectStatus().isOk()
								.returnResult(Cliente.class)
								.get;
	}*/

}
