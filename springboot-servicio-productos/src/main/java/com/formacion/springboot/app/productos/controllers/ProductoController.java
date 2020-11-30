package com.formacion.springboot.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
