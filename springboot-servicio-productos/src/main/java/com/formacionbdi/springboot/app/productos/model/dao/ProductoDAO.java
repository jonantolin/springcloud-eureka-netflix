package com.formacionbdi.springboot.app.productos.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.formacionbdi.springboot.app.productos.model.entity.Producto;

public interface ProductoDAO extends CrudRepository<Producto, Long>{

}
