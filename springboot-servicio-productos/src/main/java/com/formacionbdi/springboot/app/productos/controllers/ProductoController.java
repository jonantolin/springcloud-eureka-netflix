package com.formacionbdi.springboot.app.productos.controllers;

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

import com.formacionbdi.springboot.app.productos.model.entity.Producto;
import com.formacionbdi.springboot.app.productos.model.service.IProductoService;

@RestController
public class ProductoController {
	
	
	//Con esta variable puedo conocer el puerto en el que esta desplegado 
	//@Autowired
	//private Environment env;
	
	//Una forma aun mas simple de conocer el puerto
	@Value("${server.port}")
	private Integer port;
	
	/*
	 * Ojo, se usa la interface, no la clase implementada.
	 * Esto es una 'buena práctica' con el fin de que si hubiera que sustituir la implementacion (la clase), 
	 * no habria que modificar nada allá donde se usara la anterior. 
	 * Spring internamente buscará y usará la clase que implemente la interface.
	 */
	@Autowired
	private IProductoService productoService;

	//@GetMapping("/productos")
	@GetMapping("")
	public List<Producto> listar(){
		return productoService.findAll().stream().map( producto -> {
			//usando la interface Environment
			//producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			
			//usando @Value
			producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}
	
	//@GetMapping("/{id}")
	@GetMapping("/{id}")
	public Producto detalle(@PathVariable Long id) {
		Producto producto = productoService.findById(id);
		//usando la interface Environment
		//producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		
		//usando @Value
		producto.setPort(port);
		
		//prueba para probar timeout mayor
		
		/*try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		
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
		Producto productoDb = productoService.findById(id);
		if(producto.getNombre() != null) {
			productoDb.setNombre(producto.getNombre());
		}
		if(producto.getPrecio() != null) {
			productoDb.setPrecio(producto.getPrecio());
		}
		if(producto.getFechaCreacion() != null) {
			productoDb.setFechaCreacion(producto.getFechaCreacion());
		}
		return productoService.save(productoDb);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		productoService.deleteById(id);
	}
}
