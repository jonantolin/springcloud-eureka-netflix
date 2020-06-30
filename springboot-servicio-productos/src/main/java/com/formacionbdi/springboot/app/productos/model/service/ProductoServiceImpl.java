package com.formacionbdi.springboot.app.productos.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.springboot.app.productos.model.dao.ProductoDAO;
import com.formacionbdi.springboot.app.productos.model.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService{
	
	@Autowired
	private ProductoDAO productoDAO;

	/**
	 * Devuelve una List de Producto. 
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		
		return (List<Producto>)productoDAO.findAll();
	}

	
	/**
	 * Se le pasa un id y retorna un producto. findById() del DAO retorna un Optional, 
	 * que es un objeto con métodos para decidir qué hacer con el resultado por ejemplo si no lo encuentra en la BD.
	 * Por eso uso el orElse(null)
	 * 
	 * @param id: el id del producto
	 * @return el Producto encontrado o null
	 */
	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		
		return productoDAO.findById(id).orElse(null);
	}


	@Override
	@Transactional
	public Producto save(Producto producto) {
		return productoDAO.save(producto);
	}


	@Override
	@Transactional
	public void deleteById(Long id) {
		productoDAO.deleteById(id);
		
	}

}
