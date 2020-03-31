package com.formacionbdi.microservicios.app.cuentas.models.service;

public class SaldoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6775169530890221982L;

	public SaldoException() {
		super("Saldo no suficiente");
	}
	
	public SaldoException(String mensaje) {
		super(mensaje);
	}
	
}
