package com.formacionbdi.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter{

	private static Logger log = (Logger) LoggerFactory.getLogger(PostTiempoTranscurridoFilter.class);
	
	@Override
	public boolean shouldFilter() {
		// Validacion para aplicar filtro. Pj, para mirar si hubiera tal parametro en la ruta o no
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		log.info("Entrando a post filter");
		
		Long tiempoInicial = (Long) request.getAttribute("tiempoInicial");
		Long tiempoFinal = System.currentTimeMillis();
		Long tiempoTranscurrido = tiempoFinal - tiempoInicial;
		
		log.info(String.format("Tiempo transcurrido en segundos: %s s", tiempoTranscurrido.doubleValue() / 1000));
		log.info(String.format("Tiempo transcurrido en milisegundos: %s ms", tiempoTranscurrido));
		
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	
}
