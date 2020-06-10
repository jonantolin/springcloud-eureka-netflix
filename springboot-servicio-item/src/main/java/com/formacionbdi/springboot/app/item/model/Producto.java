package com.formacionbdi.springboot.app.item.model;

import java.util.Date;

public class Producto {

	private Long id;
	private String nombre;
	private Double precio;
	private Date fechaCreacion;
	
	//Este campo es solo para conocer el puerto del server desde donde se devuelven los datos para probar el balanceador de carga
	private Integer port;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
	
	

}
