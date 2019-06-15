package com.thefilipov.brewer.config.init;

import java.util.EnumSet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.SessionTrackingMode;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {

		/* 
		 * Define o tempo (em segundos) máximo da sessão.
		 * Independente se a aplicação está sendo utilizada ou não.
		 * Por exemplo (abaixo): 20 segundos
		 */
		// servletContext.getSessionCookieConfig().setMaxAge(20);
		
		/*
		 * Para não exibir o SessionID na URL - Segurança
		 */
		servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
		
		FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
		characterEncodingFilter.setInitParameter("encoding", "UTF-8");
		characterEncodingFilter.setInitParameter("forceEncoding", "true");
		characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");
	}
}
