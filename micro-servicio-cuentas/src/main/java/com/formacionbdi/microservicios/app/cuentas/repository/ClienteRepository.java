package com.formacionbdi.microservicios.app.cuentas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formacionbdi.microservicios.common.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
