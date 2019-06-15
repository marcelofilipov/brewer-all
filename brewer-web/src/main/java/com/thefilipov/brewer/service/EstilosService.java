package com.thefilipov.brewer.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thefilipov.brewer.model.Estilo;
import com.thefilipov.brewer.repository.Estilos;
import com.thefilipov.brewer.service.exception.ImpossivelExcluirEntidadeException;
import com.thefilipov.brewer.service.exception.NomeEstiloJaCadastradoException;

@Service
public class EstilosService {

	@Autowired
	private Estilos estilos;
	
	@Transactional
	public Estilo salvar(Estilo estilo) {
		Optional<Estilo> estiloOptional = estilos.findByNomeIgnoreCase(estilo.getNome());
		if(estiloOptional.isPresent()) {
			throw new NomeEstiloJaCadastradoException("Nome do estilo já cadastrado");
		}
		
		return estilos.saveAndFlush(estilo);
	}
	
	@Transactional
	public void excluir(Estilo estilo) {
		try {
			estilos.delete(estilo);
			estilos.flush();
		} catch(PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar estilo. Já foi usada no cadastro de cervejas.");
		}
	}

}
