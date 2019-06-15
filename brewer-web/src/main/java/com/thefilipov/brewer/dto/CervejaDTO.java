package com.thefilipov.brewer.dto;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

import com.thefilipov.brewer.model.OrigemValues;

public class CervejaDTO {

	private Long codigo;
	private String sku;
	private String nome;
	private String origem;
	private BigDecimal preco;
	private String foto;
	private String urlThumbnailFoto;

	public CervejaDTO(Long codigo, String sku, String nome, OrigemValues origem, BigDecimal preco, String foto) {
		this.codigo = codigo;
		this.sku = sku;
		this.nome = nome;
		this.origem = origem.getDescricao();
		this.preco = preco;
		this.foto = StringUtils.isEmpty(foto) ? "cerveja-mock.png" : foto;;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

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

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getUrlThumbnailFoto() {
		return urlThumbnailFoto;
	}

	public void setUrlThumbnailFoto(String urlThumbnailFoto) {
		this.urlThumbnailFoto = urlThumbnailFoto;
	}
	
}
