package com.formacionbdi.microservicios.app.cuentas.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formacionbdi.microservicios.common.entity.Cliente;
import com.formacionbdi.microservicios.common.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	public List<Producto> findByCliente(Cliente cliente);

}
