package com.formacion.springboot.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacion.springboot.app.productos.model.entity.Producto;
import com.formacion.springboot.app.productos.model.service.IProductoService;

@RestController
public class ProductoController {

	@Autowired
	private IProductoService productoService;

	@Value("${server.port}")
	private Integer port;

	@GetMapping("/listar")
	public List<Producto> listar() {

		return productoService.findAll().stream().map(producto -> {
			producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public Producto detalle(@PathVariable Long id) {
		
		// Fuerzo un sleep para probar timeout de hystrix y ribbon
		// El timeout por defecto es de 1 segundo.
		/*
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		Producto producto = productoService.findById(id);
		producto.setPort(port);
		return producto;
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return productoService.save(producto);
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		
		//Pido primero el producto como esta en la BD, porque podria llegar sin todos los campos desde el front
		Producto productoDb = productoService.findById(id);
		
		//Verifico que campos me llegan desde el front y solo cambio esos
		
		if(producto.getNombre() != null) {
			productoDb.setNombre(producto.getNombre());
		}
		
		if(producto.getPrecio() != null) {
			productoDb.setPrecio(producto.getPrecio());
		}
		
		if(producto.getCreateAt() != null) {
			productoDb.setCreateAt(producto.getCreateAt());
		}
		
		
		return productoService.save(productoDb);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		productoService.deleteById(id);
	}
	
	
}
