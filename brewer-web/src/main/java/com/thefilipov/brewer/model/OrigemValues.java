package com.thefilipov.brewer.model;

/**
 * 
 * @author Marcelo A. Filipov
 * @since 2016
 */
public enum OrigemValues {

	NACIONAL(1, "Nacional"), INTERNACIONAL(2, "Internacional");
	
	private Integer codigo;
	private String descricao;

	private OrigemValues(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static String getDescricaoById(int id) {
		String descricao = null;
		for (OrigemValues v : values()) {
			if(v.getCodigo() == id) {
				descricao = v.getDescricao();
				break;
			}
		}
		return descricao;
	}
	
	public static String getDescricaoById(long id) {
		String descricao = null;
		for (OrigemValues v : values()) {
			if(v.getCodigo() == id) {
				descricao = v.getDescricao();
				break;
			}
		}
		return descricao;
	}

	public static String getDescricaoById(short id) {
		String descricao = null;
		for (OrigemValues v : values()) {
			if(v.getCodigo() == id) {
				descricao = v.getDescricao();
				break;
			}
		}
		return descricao;
	}

	public static String getDescricaoById(byte id) {
		String descricao = null;
		for (OrigemValues v : values()) {
			if(v.getCodigo() == id) {
				descricao = v.getDescricao();
				break;
			}
		}
		return descricao;
	}
	
	public static OrigemValues parse(int id){
		OrigemValues retorno = null;
		for (OrigemValues v : values()) {
			if(v.getCodigo() == id) {
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

}
