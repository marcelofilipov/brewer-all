package com.thefilipov.brewer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.thefilipov.brewer.service.CervejasService;
import com.thefilipov.brewer.storage.FotoStorage;

@Configuration
@ComponentScan(basePackageClasses = { CervejasService.class, FotoStorage.class })
public class ServiceConfig {}	
