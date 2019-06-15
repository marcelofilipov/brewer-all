package com.thefilipov.brewer.repository.filter;

import java.math.BigDecimal;

import com.thefilipov.brewer.model.Estilo;
import com.thefilipov.brewer.model.OrigemValues;
import com.thefilipov.brewer.model.SaborValues;

public class CervejaFilter {

	private String sku;
	private String nome;
	private Estilo estilo;
	private SaborValues sabor;
	private OrigemValues origem;
	private BigDecimal precoDe;
	private BigDecimal precoAte;
	
	public String getSku() {
		return sku;
	}
	
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Estilo getEstilo() {
		return estilo;
	}
	
	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}
	
	public SaborValues getSabor() {
		return sabor;
	}
	
	public void setSabor(SaborValues sabor) {
		this.sabor = sabor;
	}
	
	public OrigemValues getOrigem() {
		return origem;
	}
	
	public void setOrigem(OrigemValues origem) {
		this.origem = origem;
	}
	
	public BigDecimal getPrecoDe() {
		return precoDe;
	}
	
	public void setPrecoDe(BigDecimal precoDe) {
		this.precoDe = precoDe;
	}
	
	public BigDecimal getPrecoAte() {
		return precoAte;
	}
	
	public void setPrecoAte(BigDecimal precoAte) {
		this.precoAte = precoAte;
	}
	
}
