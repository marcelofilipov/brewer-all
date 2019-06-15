package com.thefilipov.brewer.repository.helper.cidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thefilipov.brewer.model.Cidade;
import com.thefilipov.brewer.repository.filter.CidadeFilter;

public interface CidadesQueries {

	public Page<Cidade> filtrar(CidadeFilter filtro, Pageable pageable);
	public Cidade buscarComEstado(Long codigo);
	
}
