package com.thefilipov.brewer.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

import com.thefilipov.brewer.repository.listener.CervejaEntityListener;
import com.thefilipov.brewer.validation.SKU;

@EntityListeners(CervejaEntityListener.class)
@Entity
@Table(name = "cerveja")	
public class Cerveja implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@SKU
	@NotBlank
	private String sku;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	@Size(min = 10, max = 500, message = "O tamanho da Descrição deve ter o tamanho entre {min} e {max} caracteres")
	private String descricao;
	
	@NotNull(message = "Preço é obrigatório")
	@DecimalMin(value = "0.50", message = "O preço da cerveja deve ser maior que R$0,50")
	@DecimalMax(value = "9999999.99", message = "O preço da cerveja deve ser menor que R$9.999.999,99")
	private BigDecimal preco;
	
	@NotNull(message = "O teor alcóolico é obrigatório")
	@DecimalMax(value = "100.0", message = "O valor do teor alcóolico deve ser menor que 100")
	@Column(name = "teor_alcoolico")
	private BigDecimal teorAlcoolico;
	
	@NotNull(message = "Comissão é obrigatória")
	@DecimalMax(value = "100.0", message = "A comissão deve ser igual ou menor que 100")
	private BigDecimal comissao;
	
	@NotNull(message = "A quantidade em estoque é obrigatória")
	@Max(value = 9999, message = "A quantidade em estoque deve ser menor que 9.999")
	@Column(name = "qtde_estoque")
	private Integer qtdeEstoque;

	@NotNull(message = "Origem é obrigatório")
	@Enumerated(EnumType.STRING)
	private OrigemValues origem;
	
	@NotNull(message = "Sabor é obrigatório")
	@Enumerated(EnumType.STRING)
	private SaborValues sabor;
	
	@NotNull(message = "Estilo é obrigatório")
	@ManyToOne
	@JoinColumn(name = "codigo_estilo")
	private Estilo estilo;

	private String foto;
	
	@Column(name = "content_type")
	private String contentType;
	
	@Transient
	private boolean novaFoto;
	
	@Transient
	private String urlFoto;
	
	@Transient
	private String urlThumbnailFoto;
	
	@PrePersist @PreUpdate
	private void prePersistUpdate() {
		sku = sku.toUpperCase();
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public BigDecimal getTeorAlcoolico() {
		return teorAlcoolico;
	}

	public void setTeorAlcoolico(BigDecimal teorAlcoolico) {
		this.teorAlcoolico = teorAlcoolico;
	}

	public BigDecimal getComissao() {
		return comissao;
	}

	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}

	public Integer getQtdeEstoque() {
		return qtdeEstoque;
	}

	public void setQtdeEstoque(Integer qtdeEstoque) {
		this.qtdeEstoque = qtdeEstoque;
	}

	public OrigemValues getOrigem() {
		return origem;
	}

	public void setOrigem(OrigemValues origem) {
		this.origem = origem;
	}

	public SaborValues getSabor() {
		return sabor;
	}

	public void setSabor(SaborValues sabor) {
		this.sabor = sabor;
	}

	public Estilo getEstilo() {
		return estilo;
	}
	
	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFotoOuMock() {
		return !StringUtils.isEmpty(foto) ? foto : "cerveja-mock.png";
	}
	
	public boolean temFoto() {
		return !StringUtils.isEmpty(this.foto);
	}
	
	public boolean isNova() {
		return codigo == null;
	}
	
	public boolean isNovaFoto() {
		return novaFoto;
	}

	public void setNovaFoto(boolean novaFoto) {
		this.novaFoto = novaFoto;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}
	
	public String getUrlThumbnailFoto() {
		return urlThumbnailFoto;
	}

	public void setUrlThumbnailFoto(String urlThumbnailFoto) {
		this.urlThumbnailFoto = urlThumbnailFoto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cerveja other = (Cerveja) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
