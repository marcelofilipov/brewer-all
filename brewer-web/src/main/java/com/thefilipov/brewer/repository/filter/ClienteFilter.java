package com.thefilipov.brewer.repository.filter;

import com.thefilipov.brewer.model.TipoPessoaValues;

public class ClienteFilter {

	private String nome;
	private String cpfOuCnpj;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}
	
	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}
	
	public Object getCpfOuCnpjSemFormatacao() {
		return TipoPessoaValues.removerFormatacao(this.cpfOuCnpj);
	}
}
