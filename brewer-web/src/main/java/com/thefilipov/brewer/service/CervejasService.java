package com.thefilipov.brewer.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thefilipov.brewer.model.Cerveja;
import com.thefilipov.brewer.repository.Cervejas;
import com.thefilipov.brewer.service.exception.ImpossivelExcluirEntidadeException;
import com.thefilipov.brewer.storage.FotoStorage;

@Service
public class CervejasService {

	@Autowired
	private Cervejas cervejas;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@Transactional
	public void salvar(Cerveja cerveja) {
		cervejas.save(cerveja);
	}
	
	@Transactional
	public void excluir(Cerveja cerveja) {
		try {
			String foto = cerveja.getFoto();
			cervejas.delete(cerveja);
			cervejas.flush();
			fotoStorage.excluir(foto);
		} catch(PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar cerveja. Já foi usada em alguma venda.");
		}
	}
	
}
