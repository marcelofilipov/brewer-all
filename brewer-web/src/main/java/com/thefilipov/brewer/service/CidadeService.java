package com.thefilipov.brewer.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thefilipov.brewer.model.Cidade;
import com.thefilipov.brewer.repository.Cidades;
import com.thefilipov.brewer.service.exception.ImpossivelExcluirEntidadeException;
import com.thefilipov.brewer.service.exception.NomeCidadeJaCadastradaException;

@Service
public class CidadeService {

	@Autowired
	private Cidades cidades;
	
	@Transactional
	public void salvar(Cidade cidade) {
		Optional<Cidade> cidadeExistente = cidades.findByNomeAndEstado(cidade.getNome(), cidade.getEstado());
		if (cidadeExistente.isPresent()) {
			throw new NomeCidadeJaCadastradaException("Nome de cidade já cadastrado");
		}
		
		cidades.save(cidade);
	}

	@Transactional
	public void excluir(Cidade cidade) {
		try {
			cidades.delete(cidade);
			cidades.flush();
		} catch(PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar cidade. Já foi usada no cadastro de clientes.");
		}
	}
	
}
