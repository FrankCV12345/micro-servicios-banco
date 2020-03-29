package com.formacionbdi.microservicios.app.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formacionbdi.microservicios.common.entity.Cliente;


public interface IRepositoryCliente extends JpaRepository<Cliente, Long> {

}
