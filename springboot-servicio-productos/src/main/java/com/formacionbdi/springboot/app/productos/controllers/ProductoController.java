package com.formacionbdi.springboot.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.productos.model.entity.Producto;
import com.formacionbdi.springboot.app.productos.model.service.IProductoService;

@RestController
public class ProductoController {
	
 /*
 * Ojo, se usa la interface, no la clase implementada.
 * Esto es una 'buena práctica' con el fin de que si hubiera que sustituir la implementacion (la clase), 
 * no habria que modificar nada allá donde se usara la anterior. 
 * Spring internamente buscará y usará la clase que implemente la interface.
 */
	
	
	
	//Con esta variable puedo conocer el puerto en el que esta desplegado 
	//@Autowired
	//private Environment env;
	
	//Una forma aun mas simple de conocer el puerto
	@Value("${server.port}")
	private Integer port;
	
	@Autowired
	private IProductoService productoService;


	@GetMapping("/productos")
	public List<Producto> listar(){
		return productoService.findAll().stream().map( producto -> {
			//usando la interface Environment
			//producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			
			//usando @Value
			producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/productos/{id}")
	public Producto detalle(@PathVariable Long id) {
		Producto producto = productoService.findById(id);
		//usando la interface Environment
		//producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		
		//usando @Value
		producto.setPort(port);
		return producto;
	}
}
