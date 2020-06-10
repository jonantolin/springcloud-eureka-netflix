package com.formacionbdi.springboot.app.item.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.item.model.Item;
import com.formacionbdi.springboot.app.item.service.IItemService;

@RestController
public class ItemController {

	//Una alternativa a Qualifier es usar la anotacion @Primary en el servicio que queramos utilizar
	// @Qualifier("serviceRestTemplate") //usando servicio cliente RestTemplate
	// @Qualifier("serviceFeign") // usando servicio cliente Feign
	@Autowired
	@Qualifier("serviceFeign") // usando servicio cliente Feign
	private IItemService itemService;
	
	@GetMapping("/items")
	public List<Item> listar(){
		return itemService.findAll();
	}
	
	@GetMapping("/items/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
		return itemService.findById(id, cantidad);
	}
}
