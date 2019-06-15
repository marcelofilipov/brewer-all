package com.thefilipov.brewer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thefilipov.brewer.model.Cidade;
import com.thefilipov.brewer.model.Estado;
import com.thefilipov.brewer.repository.helper.cidade.CidadesQueries;

public interface Cidades extends JpaRepository<Cidade, Long>, CidadesQueries {
	
	public List<Cidade> findByEstadoCodigo(Long codigoEstado);
	public Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);

}
