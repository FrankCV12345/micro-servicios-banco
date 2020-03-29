package com.formacionbdi.microservicios.app.cuentas.models.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionbdi.microservicios.app.cuentas.repository.ClienteRepository;
import com.formacionbdi.microservicios.app.cuentas.repository.LibreDiarioRepository;
import com.formacionbdi.microservicios.app.cuentas.repository.ProductoRepository;
import com.formacionbdi.microservicios.common.entity.Cliente;
import com.formacionbdi.microservicios.common.entity.LibroDiario;
import com.formacionbdi.microservicios.common.entity.Producto;

import io.reactivex.Single;

@Service
public class CuentaServiceImplements implements ICuentaService {
	private final String OperacionRetiro = "retiro";
	private final String OperacionDeposito = "deposito";

	@Autowired
	private ProductoRepository productoRepository;
	
	
	@Autowired
	private LibreDiarioRepository libroDiarioRepository;
	
	@Override
	public Single<Producto> crear(Producto producto) {
		// TODO Auto-generated method stub
		return Single.create( (s) ->{
			if(producto.getSaldo() == 0 || producto.getSaldo() == null || producto.getSaldo() < 100) {
				s.onError(new EntityNotFoundException());
			}else if (producto.getSaldo() >= 100) {
				
				Producto pro = productoRepository.save(producto);
				libroDiarioRepository.save(new LibroDiario("deposito", producto.getSaldo(), producto));
				
				s.onSuccess(pro);
			}
			
		});
	}

	@Override
	public Single<List<Producto>> ListarProductoPorIdCliente(Long id) {
		// TODO Auto-generated method stub
		return Single.create((s) ->{
			Cliente c = new Cliente(id);
			List<Producto> productos =  productoRepository.findByCliente(c);
				s.onSuccess(productos);
		
		});
	}

	@Override
	public Single<Double> retiro(Transacion transacion) {
		// TODO Auto-generated method stub
		return Single.create((s) -> {
			Producto p = validaOperacion(transacion.getIdProdcuto());
			if(p != null) {
				Double saldo  =ejecutaTransaccion(transacion , this.OperacionRetiro,p);
				if(saldo != null) {
					s.onSuccess(saldo);	
				}else {
					s.onError(new SaldoException());
				}
			}else {
				s.onError( new EntityNotFoundException());
			}
		});
	}

	@Override
	public Single<Double> deposito(Transacion transacion) {
		return Single.create((s) -> {
			Producto p = validaOperacion(transacion.getIdProdcuto());
			if(p != null) {
				Double saldo  =ejecutaTransaccion(transacion , this.OperacionDeposito,p);
				if(saldo != null) {
					s.onSuccess(saldo);	
				}else {
					s.onError( new SaldoException());
				}
			}else {
				s.onError( new EntityNotFoundException());
			}
			
		});
	}
	
	//retorna null si al hacer retiro no hay saldo suficiente o si entra al default del case
	private Double ejecutaTransaccion(Transacion t ,String tipoOperacion,Producto p ) {
		Double saldo = 0.0;
		switch (tipoOperacion ) {
			case OperacionRetiro:
				if(p.getSaldo() >  t.getMonto()) {
					libroDiarioRepository.save(new LibroDiario(this.OperacionRetiro, t.getMonto(), p));
					p.setSaldo(p.getSaldo() - t.getMonto());
					productoRepository.save(p);
					saldo = p.getSaldo();
				}else {
					saldo = null;
				}
				break;
			case OperacionDeposito:
				p.setSaldo(p.getSaldo()  + t.getMonto());
				productoRepository.save(p);
				saldo = p.getSaldo();
				libroDiarioRepository.save(new LibroDiario(this.OperacionDeposito, t.getMonto(), p));
				break;
			default:
				saldo  = null;
				break;
		}	
		return saldo;
	}
	
	//valida si la cuenta existe
	private Producto validaOperacion(Long idProducto) {
		Optional<Producto> p = productoRepository.findById(idProducto);
		if(p.isPresent()) {
			return p.get();
		}else {
			return null;
		}
		
	}

}
