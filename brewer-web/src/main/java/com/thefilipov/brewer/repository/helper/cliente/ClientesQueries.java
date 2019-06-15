package com.thefilipov.brewer.repository.helper.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thefilipov.brewer.model.Cliente;
import com.thefilipov.brewer.repository.filter.ClienteFilter;

public interface ClientesQueries {

	public Page<Cliente> filtrar(ClienteFilter filtro, Pageable pageable);
	public Cliente findByCodigo(Long codigo);
	
}
