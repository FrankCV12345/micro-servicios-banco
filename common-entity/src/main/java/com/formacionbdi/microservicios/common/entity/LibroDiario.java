package com.formacionbdi.microservicios.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="tb_libro_diario")
public class LibroDiario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	private String tipoTransMovimiento;
	private Double monto;
	@ManyToOne
	private Producto producto;
	@Column(name="create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@PrePersist
	public void prePersistent() {
		this.createAt = new Date();
	}


	public LibroDiario(Producto producto) {
		
		this.producto = producto;
	}


	public LibroDiario(String tipoTransMovimiento, Double montol, Producto producto) {
		super();
		this.tipoTransMovimiento = tipoTransMovimiento;
		this.monto = montol;
		this.producto = producto;
	}


	public Long getId() {
		return id;
	}


	public String getTipoTransMovimiento() {
		return tipoTransMovimiento;
	}

	public void setTipoTransMovimiento(String tipoTransMovimiento) {
		this.tipoTransMovimiento = tipoTransMovimiento;
	}

	public Double getMontol() {
		return monto;
	}

	public void setMontol(Double montol) {
		this.monto = montol;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	
	
}
