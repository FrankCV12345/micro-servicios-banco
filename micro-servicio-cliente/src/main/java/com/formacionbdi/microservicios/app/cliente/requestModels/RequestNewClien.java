package com.formacionbdi.microservicios.app.cliente.requestModels;

import com.formacionbdi.microservicios.common.entity.Cliente;
import com.formacionbdi.microservicios.common.entity.Producto;

public class RequestNewClien {
	private Cliente cliente;
	private Producto producto;
	public RequestNewClien(Cliente cliente, Producto producto) {
		this.cliente = cliente;
		this.producto = producto;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
}
