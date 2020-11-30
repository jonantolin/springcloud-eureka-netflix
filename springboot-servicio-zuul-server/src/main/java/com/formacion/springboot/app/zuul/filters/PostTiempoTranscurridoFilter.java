package com.formacion.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter{
	
	private static Logger log = LoggerFactory.getLogger(PostTiempoTranscurridoFilter.class);

	@Override
	public boolean shouldFilter() {
		// Sirve para validar algo (parametro recibido, etc) pero en este caso
		// no quiero que valide nada y se use el filtro siempre, por tanto true
		return true;
	}

	@Override
	public Object run() throws ZuulException {

		//Obtengo el contexto
		RequestContext ctx = RequestContext.getCurrentContext();
		
		// obtengo la peticion
		HttpServletRequest request = ctx.getRequest();
		
		log.info("Entrando a post");
		
		// Cojo el tiempo de inicio de la request introducido como atributo en el filtro pre
		// y hago la resta
		Long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
		Long tiempoFinal = System.currentTimeMillis();
		Long tiempoTranscurrido = tiempoFinal - tiempoInicio;
		
		log.info(String.format("Tiempo transcurrido en segundos: %s s", tiempoTranscurrido.doubleValue()/1000));
		log.info(String.format("Tiempo transcurrido en milisegundos: %s ms", tiempoTranscurrido));
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 2;
	}

}
