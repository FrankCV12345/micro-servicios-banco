package com.formacionbdi.microservicios.app.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.formacionbdi.microservicios.common.entity"})
public class MicroServicioClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServicioClienteApplication.class, args);
	}

}

