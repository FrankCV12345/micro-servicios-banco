package com.formacionbdi.microservicios.app.cuentas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formacionbdi.microservicios.common.entity.LibroDiario;

public interface LibreDiarioRepository extends JpaRepository<LibroDiario, Long> {

}
