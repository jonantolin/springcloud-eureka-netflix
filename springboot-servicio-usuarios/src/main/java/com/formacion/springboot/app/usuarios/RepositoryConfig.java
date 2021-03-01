package com.formacion.springboot.app.usuarios;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.formacion.springboot.app.commons.usuarios.models.entity.Rol;
import com.formacion.springboot.app.commons.usuarios.models.entity.Usuario;

/**
 * La utilizo para que retorne el ID en las peticiones rest, por defecto no lo hace
 * @author Jon
 *
 */
@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer{

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Usuario.class, Rol.class);
	}

	
}
