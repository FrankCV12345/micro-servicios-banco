package com.formacionbdi.microservicios.app.cuentas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.formacionbdi.microservicios.common.entity"})
public class MicroServicioCuentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServicioCuentasApplication.class, args);
	}

}
