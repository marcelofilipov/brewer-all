package com.thefilipov.brewer.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thefilipov.brewer.model.Cliente;
import com.thefilipov.brewer.repository.Clientes;
import com.thefilipov.brewer.service.exception.CpfCnpjClienteJaCadastradoException;
import com.thefilipov.brewer.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class ClienteService {

	@Autowired
	private Clientes clientes;
	
	@Transactional
	public void salvar(Cliente cliente) {
		Optional<Cliente> clienteExistente = clientes.findByCpfOuCnpj(cliente.getCpfOuCnpjSemFormatacao());
		if(clienteExistente.isPresent()) {
			throw new CpfCnpjClienteJaCadastradoException("CPF/CNPJ já cadastrado.");
		}
		clientes.save(cliente);
	}
	
	@Transactional
	public void excluir(Cliente cliente) {
		try {
			clientes.delete(cliente);
			clientes.flush();
		} catch(PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar cliente. Já foi usada no cadastro de vendas.");
		}
	}

}
