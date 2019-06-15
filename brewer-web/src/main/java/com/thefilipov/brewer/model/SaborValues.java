package com.thefilipov.brewer.model;

/**
 * 
 * @author Marcelo A. Filipov
 * @since 2016
 */
public enum SaborValues {

	ADOCICADA(1, "Adocicada"), AMARGA(2, "Amarga"), FORTE(3, "Forte"), FRUTADA(4, "Frutada"), SUAVE(5, "Suave");
	
	private Integer codigo;
	private String descricao;

	private SaborValues(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static String getDescricaoById(int id) {
		String descricao = null;
		for (SaborValues v : values()) {
			if(v.getCodigo() == id) {
				descricao = v.getDescricao();
				break;
			}
		}
		return descricao;
	}
	
	public static String getDescricaoById(long id) {
		String descricao = null;
		for (SaborValues v : values()) {
			if(v.getCodigo() == id) {
				descricao = v.getDescricao();
				break;
			}
		}
		return descricao;
	}

	public static String getDescricaoById(short id) {
		String descricao = null;
		for (SaborValues v : values()) {
			if(v.getCodigo() == id) {
				descricao = v.getDescricao();
				break;
			}
		}
		return descricao;
	}

	public static String getDescricaoById(byte id) {
		String descricao = null;
		for (SaborValues v : values()) {
			if(v.getCodigo() == id) {
				descricao = v.getDescricao();
				break;
			}
		}
		return descricao;
	}
	
	public static SaborValues parse(int id){
		SaborValues retorno = null;
		for (SaborValues v : values()) {
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
