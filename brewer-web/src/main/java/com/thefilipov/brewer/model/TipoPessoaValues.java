package com.thefilipov.brewer.model;

import com.thefilipov.brewer.model.validation.group.CnpjGroup;
import com.thefilipov.brewer.model.validation.group.CpfGroup;

/**
 * 
 * @author Marcelo A. Filipov
 * @since 2016
 */
public enum TipoPessoaValues {

	FISICA(1, "CPF", "Física", "000.000.000-00", CpfGroup.class) {
		@Override
		public String formatar(String cpfOuCnpj) {
			return cpfOuCnpj.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
		}
	},
	JURIDICA(2, "CNPJ", "Jurídica", "00.000.000/0000-00", CnpjGroup.class) {
		@Override
		public String formatar(String cpfOuCnpj) {
			return cpfOuCnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})", "$1.$2.$3/$4-");
		}
	};

	private Integer codigo;
	private String documento;
	private String descricao;
	private String mascara;
	private Class<?> grupo;

	private TipoPessoaValues(int id, String documento, String descricao, String mascara, Class<?> grupo) {
		this.codigo = id;
		this.documento = documento;
		this.descricao = descricao;
		this.mascara = mascara;
		this.grupo = grupo;
	}
	
	public abstract String formatar(String cpfOuCnpj);

	public static String getDescricaoById(byte id) {
		String descricao = null;
		for (TipoPessoaValues v : values()) {
			if (v.getCodigo() == id) {
				descricao = v.getDescricao();
				break;
			}	
		}
		return descricao;
	}

	public static String getDescricaoById(short id) {
		String descricao = null;
		for (TipoPessoaValues v : values()) {
			if (v.getCodigo() == id) {
				descricao = v.getDescricao();
				break;
			}
		}
		return descricao;
	}

	public static String getDescricaoById(int id) {
		String descricao = null;
		for (TipoPessoaValues v : values()) {
			if (v.getCodigo() == id) {
				descricao = v.getDescricao();
				break;
			}
		}
		return descricao;
	}
	
	public static String getDescricaoById(long id) {
		String descricao = null;
		for (TipoPessoaValues v : values()) {
			if (v.getCodigo() == id) {
				descricao = v.getDescricao();
				break;
			}
		}
		return descricao;
	}

	public static String getDocumentoById(byte id) {
		String documento = null;
		for (TipoPessoaValues v : values()) {
			if (v.getCodigo() == id) {
				documento = v.getDocumento();
				break;
			}	
		}
		return documento;
	}

	public static String getDocumentoById(short id) {
		String documento = null;
		for (TipoPessoaValues v : values()) {
			if (v.getCodigo() == id) {
				documento = v.getDocumento();
				break;
			}
		}
		return documento;
	}

	public static String getDocumentoById(int id) {
		String documento = null;
		for (TipoPessoaValues v : values()) {
			if (v.getCodigo() == id) {
				documento = v.getDocumento();
				break;
			}
		}
		return documento;
	}
	
	public static String getDocumentoById(long id) {
		String documento = null;
		for (TipoPessoaValues v : values()) {
			if (v.getCodigo() == id) {
				documento = v.getDocumento();
				break;
			}
		}
		return documento;
	}
	
	public static TipoPessoaValues parse(int id){
		TipoPessoaValues retorno = null;
		for (TipoPessoaValues v: values()){
			if (v.getCodigo() == id) {
				retorno = v;
				break;
			}
		}
		return retorno;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getDocumento() {
		return documento;
	}

	public String getMascara() {
		return mascara;
	}

	public Class<?> getGrupo() {
		return grupo;
	}
	
	public static String removerFormatacao(String cpfOuCnpj) {
		return cpfOuCnpj.replaceAll("\\.|-|/", "");
	}

}
