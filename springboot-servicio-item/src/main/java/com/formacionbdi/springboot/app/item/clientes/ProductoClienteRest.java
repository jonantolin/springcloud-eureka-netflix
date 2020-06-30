package com.formacionbdi.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.formacionbdi.springboot.app.item.model.Producto;

@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {

	//@GetMapping("/productos") -> Uso esta ruta a traves de la puerta de enlace Zuul
	@GetMapping("")
	public List<Producto> listar();
	
	//@GetMapping("/productos/{id}") -> Uso esta ruta a traves de la puerta de enlace Zuul
	@GetMapping("/{id}")
	public Producto detalle(@PathVariable Long id);
	
	@PostMapping("/crear")
	public Producto create(@RequestBody Producto producto);
	
	@PutMapping("/editar/{id}")
	public Producto update(@RequestBody Producto producto, @PathVariable Long id);
	
	@DeleteMapping("/eliminar/{id}")
	public void delete(@PathVariable Long id);
}
