package com.formacionbdi.springboot.app.item.controllers;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.item.model.Item;
import com.formacionbdi.springboot.app.item.service.IItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
public class ItemController {

	private static Logger log = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private Environment env;
	
	//Una alternativa a Qualifier es usar la anotacion @Primary en el servicio que queramos utilizar
	// @Qualifier("serviceRestTemplate") //usando servicio cliente RestTemplate
	// @Qualifier("serviceFeign") // usando servicio cliente Feign
	@Autowired
	@Qualifier("serviceFeign") // usando servicio cliente Feign
	private IItemService itemService;
	
	@Value("${configuracion.texto}")
	private String textoConfig;
	
	//@GetMapping("/items")
	@GetMapping("")
	public List<Item> listar(){
		return itemService.findAll();
	}
	
	//@HystrixCommand(fallbackMethod = "metodoAlternativo")
	/*@GetMapping("/items/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}*/
	
	//@GetMapping("/items/{id}/cantidad/{cantidad}")
	
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/{id}/cantidad/{cantidad}")
	public ResponseEntity<?> detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		
		return new ResponseEntity<Item>(itemService.findById(id, cantidad), HttpStatus.OK);			
	}
	
	/**
	 * Se ejecuta si el metodo detalle() falla
	 * @param id
	 * @param cantidad
	 * @return ResponseEntity<Map<String, Object>> que devuelve mensaje con estado http 404
	 */
	public ResponseEntity<?> metodoAlternativo(Long id, Integer cantidad){
		
		Map<String, Object> response = new HashMap<>();
		
		response.put("mensaje", "producto no encontrado");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/obtener-config")
	public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto){
		
		log.info(textoConfig);
		
		Map<String, String> json = new HashMap<>();
		json.put("mensaje", textoConfig);
		json.put("port", puerto);
		
		if(env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("desarrollo")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email", env.getProperty("configuracion.autor.email"));
		}
		
		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}
	
	/*
	@GetMapping("/items/{id}/cantidad/{cantidad}")
	public ResponseEntity<?> detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
	
		Item item = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			item = itemService.findById(id, cantidad);
		} catch(Exception e) {
			response.put("mensaje", "Error al realizar la consulta al servicio");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Item>(item, HttpStatus.OK);
	}
	*/
}
