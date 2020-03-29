package com.formacionbdi.microservicios.app.cuentas.models.service;

public class Transacion{
	private Long idProdcuto;
	private Double monto;
	
	
	
	public Long getIdProdcuto() {
		return idProdcuto;
	}
	public void setIdProdcuto(Long idProdcuto) {
		this.idProdcuto = idProdcuto;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public Transacion(Long idProdcuto, Double monto) {
		this.idProdcuto = idProdcuto;
		this.monto = monto;
	}
	
	
}
