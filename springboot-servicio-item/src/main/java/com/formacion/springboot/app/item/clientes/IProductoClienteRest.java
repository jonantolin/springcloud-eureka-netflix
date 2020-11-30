package com.formacion.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.formacion.springboot.app.item.models.Producto;

@FeignClient(name="servicio-productos")
public interface IProductoClienteRest {
	
	//GetMapping al ENDPOINT al que nos queremos conectar (el de servicio-productos)
	@GetMapping("/listar")
	public List<Producto> listar();
	
	@GetMapping("/{id}")
	public Producto detalle(@PathVariable Long id);

}
