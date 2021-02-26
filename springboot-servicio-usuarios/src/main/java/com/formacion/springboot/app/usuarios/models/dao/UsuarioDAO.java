package com.formacion.springboot.app.usuarios.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.formacion.springboot.app.usuarios.models.entity.Usuario;

/**
 * Hereda a su vez de CrudRepository, pero es mas completa
 */
@RepositoryRestResource(path = "usuarios")
public interface UsuarioDAO extends PagingAndSortingRepository<Usuario, Long> {

	//Metodo por convencion de Spring Data (findBy)
	public Usuario findByUsername(String username);
	
	//Metodo 'tradicional', consulta referenciada a objetos (no a tablas)
	@Query("select u from Usuario u where u.username=?1")
	public Usuario obtenerPorUsername(String username);
}
