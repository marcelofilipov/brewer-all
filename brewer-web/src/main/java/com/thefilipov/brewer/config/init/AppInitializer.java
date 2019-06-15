package com.thefilipov.brewer.config.init;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.thefilipov.brewer.config.JPAConfig;
import com.thefilipov.brewer.config.MailConfig;
import com.thefilipov.brewer.config.S3Config;
import com.thefilipov.brewer.config.SecurityConfig;
import com.thefilipov.brewer.config.ServiceConfig;
import com.thefilipov.brewer.config.WebConfig;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { JPAConfig.class, ServiceConfig.class, SecurityConfig.class, S3Config.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class, MailConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

/*
 * Esse método funcionava até antes da implementação do Spring Security
 * 
 */
//	@Override
//	protected Filter[] getServletFilters() {
//		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//		characterEncodingFilter.setEncoding("UTF-8");
//		characterEncodingFilter.setForceEncoding(true);
//		
//		return new Filter[] { characterEncodingFilter };
//	}

	@Override
	protected Filter[] getServletFilters() {
		HttpPutFormContentFilter httpPutFormContentFilter = new HttpPutFormContentFilter();
		return new Filter[] { httpPutFormContentFilter };
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
		super.customizeRegistration(registration);
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.setInitParameter("spring.profiles.default", "storage-s3, LO");
	}

}
