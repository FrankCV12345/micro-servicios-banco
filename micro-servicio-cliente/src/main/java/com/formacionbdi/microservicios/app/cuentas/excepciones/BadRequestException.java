package com.formacionbdi.microservicios.app.cuentas.excepciones;

public class BadRequestException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6775169530890221982L;

	public BadRequestException() {
		super("Saldo no suficiente");
	}
	
	public BadRequestException(String mensaje) {
		super(mensaje);
	}
	
}
