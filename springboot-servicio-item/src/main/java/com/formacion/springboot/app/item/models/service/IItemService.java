package com.formacion.springboot.app.item.models.service;

import java.util.List;

import com.formacion.springboot.app.item.models.Item;
import com.formacion.springboot.app.item.models.Producto;

public interface IItemService {
	
	public List<Item> findAll();
	public Item findById(Long id, Integer cantidad);
	
	public Producto save(Producto producto);
	
	public Producto update(Producto producto, Long id);
	
	public void delete(Long id);

}
