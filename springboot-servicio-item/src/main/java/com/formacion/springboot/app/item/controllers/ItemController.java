package com.formacion.springboot.app.item.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacion.springboot.app.item.models.Item;
import com.formacion.springboot.app.item.models.Producto;
//import com.formacion.springboot.app.item.models.Producto;
import com.formacion.springboot.app.item.models.service.IItemService;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope //Permite refrescar cambios de configuracion sin reiniciar la maquina
@RestController
public class ItemController {
	
	private static Logger log = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceRestTemplate") //("serviceRestTemplate para usar cliente RestTemplate") / (serviceFeign para usar cliente Feign)
	private IItemService itemService;
	
	@Value("${configuracion.texto}")
	private String texto;
	
	@GetMapping("/obtener-config")
	public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String port){
		log.info(texto);
		
		Map<String, String> json = new HashMap<>();
		json.put("texto", texto);
		json.put("puerto", port);
		
		//Compruebo si el profile activo es desarrollo. getActiveProfile() devuelve un array
		if(env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("desarrollo")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email", env.getProperty("configuracion.autor.email"));
		}
		
		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/listar")
	public List<Item> listar(){
		return itemService.findAll();
	}
	
	@GetMapping("/{id}/cantidad/{cantidad}")
	public ResponseEntity<?> detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
	
		Item item = null;
		Map<String, String> response = new HashMap<>();
		
		try {
			item = itemService.findById(id, cantidad);
		} catch(Exception e) {
			response.put("mensaje", "Error al realizar la consulta al servicio");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Item>(item, HttpStatus.OK);
	}
	
	/*
	
	@HystrixCommand(fallbackMethod = "metodoAlternativo") //prueba de ejemplo de Hystrix
	@GetMapping("/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) throws Exception{
		Item item = new Item();
		try {
			item = itemService.findById(id, cantidad);
		}catch(Exception e){
			throw new Exception();
		}
		
		return item;
	}
	
	*/
	
	/**
	 * Metodo alternativo de ejemplo manejado por Hystrix si falla el metodo detalle()
	 */
	
	/*
	public Item metodoAlternativo(Long id, Integer cantidad) {
		Producto p = new Producto();
		p.setNombre("Producto por defecto");
		return new Item(p, 1);
	}
	
	
	*/
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		
		return itemService.save(producto);
	}
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto, @PathVariable Long id) {
		
		return itemService.update(producto, id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		itemService.delete(id);
	}
	
}
