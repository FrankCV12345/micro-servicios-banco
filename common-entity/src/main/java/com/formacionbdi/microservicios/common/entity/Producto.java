package com.formacionbdi.microservicios.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_cuentas")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	private String nroCuente;
	@ManyToOne
	private TipoCuenta tipoCuenta;
	private Double saldo;
	@OneToOne
	private Cliente cliente;
	@Column(name="create_At")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	
	@PrePersist
	private void prePersist() {
		this.createAt = new Date();
	}
	public Producto() {
		
	}
	public Producto(Long id) {	
		this.id = id;
	}
	
	public Producto(String nroCuente, TipoCuenta tipoCuenta, Double saldo) {
		super();
		this.nroCuente = nroCuente;
		this.tipoCuenta = tipoCuenta;
		this.saldo = saldo;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNroCuente() {
		return nroCuente;
	}
	public void setNroCuente(String nroCuente) {
		this.nroCuente = nroCuente;
	}
	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	
	 
}
