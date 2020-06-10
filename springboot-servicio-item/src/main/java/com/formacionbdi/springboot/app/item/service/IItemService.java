package com.formacionbdi.springboot.app.item.service;

import java.util.List;

import com.formacionbdi.springboot.app.item.model.Item;

public interface IItemService {

	public List<Item> findAll();
	
	public Item findById(Long id, Integer cantidad);
}
