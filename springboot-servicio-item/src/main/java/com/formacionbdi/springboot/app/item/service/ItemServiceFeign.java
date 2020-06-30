package com.formacionbdi.springboot.app.item.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionbdi.springboot.app.item.clientes.ProductoClienteRest;
import com.formacionbdi.springboot.app.item.model.Item;
import com.formacionbdi.springboot.app.item.model.Producto;

@Service("serviceFeign")
public class ItemServiceFeign implements IItemService {
	
	@Autowired
	private ProductoClienteRest clienteFeing;
	
	@Override
	public List<Item> findAll() {
		return clienteFeing.listar().stream().map( producto -> new Item(producto, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(clienteFeing.detalle(id), cantidad);
	}

	@Override
	public Producto create(Producto producto) {
		return clienteFeing.create(producto);
	}

	@Override
	public Producto update(Producto producto, Long id) {
		return clienteFeing.update(producto, id);
	}

	@Override
	public void delete(Long id) {
		clienteFeing.delete(id);
	}

}
