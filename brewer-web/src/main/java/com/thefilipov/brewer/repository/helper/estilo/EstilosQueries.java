package com.thefilipov.brewer.repository.helper.estilo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thefilipov.brewer.model.Estilo;
import com.thefilipov.brewer.repository.filter.EstiloFilter;

public interface EstilosQueries {

	public Page<Estilo> filtrar(EstiloFilter filtro, Pageable pageable);
	
}
