package com.formacionbdi.microservicios.app.cuentas.requestModels;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseSaldo {

	public ResponseEntity<Object> RespuestaSaldo(Double saldo){
		Map<String, Object> rpta = new HashMap<>();
		rpta.put("saldo", saldo);
		rpta.put("statusCode", HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(rpta);
	}

}
