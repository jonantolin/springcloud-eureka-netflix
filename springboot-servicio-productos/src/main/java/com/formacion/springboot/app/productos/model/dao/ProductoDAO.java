package com.formacion.springboot.app.productos.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.formacion.springboot.app.commons.models.entity.Producto;

public interface ProductoDAO extends CrudRepository<Producto, Long>{

}
