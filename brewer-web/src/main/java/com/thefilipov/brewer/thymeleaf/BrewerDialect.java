package com.thefilipov.brewer.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.thefilipov.brewer.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.thefilipov.brewer.thymeleaf.processor.MenuAttributeTagProcessor;
import com.thefilipov.brewer.thymeleaf.processor.MessageElementTagProcessor;
import com.thefilipov.brewer.thymeleaf.processor.OrderElementTagProcessor;
import com.thefilipov.brewer.thymeleaf.processor.PaginationElementTagProcessor;

public class BrewerDialect extends AbstractProcessorDialect {

	public BrewerDialect() {
		super("TheFilipov", "brewer", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAttributeTagProcessor(dialectPrefix));
		
		return processadores;
	}

}
