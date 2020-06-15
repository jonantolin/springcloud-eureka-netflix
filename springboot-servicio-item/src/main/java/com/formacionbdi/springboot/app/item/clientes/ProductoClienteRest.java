package com.formacionbdi.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.formacionbdi.springboot.app.item.model.Producto;

@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {

	//@GetMapping("/productos") -> Uso esta ruta a traves de la puerta de enlace Zuul
	@GetMapping("")
	public List<Producto> listar();
	
	//@GetMapping("/productos/{id}") -> Uso esta ruta a traves de la puerta de enlace Zuul
	@GetMapping("/{id}")
	public Producto detalle(@PathVariable Long id);
}
