package com.formacionbdi.springboot.app.item.service;

import java.util.List;

import com.formacionbdi.springboot.app.item.model.Item;
import com.formacionbdi.springboot.app.item.model.Producto;

public interface IItemService {

	public List<Item> findAll();
	
	public Item findById(Long id, Integer cantidad);
	
	public Producto create(Producto producto);
	
	public Producto update(Producto producto, Long id);
	
	public void delete(Long id);
	
}
