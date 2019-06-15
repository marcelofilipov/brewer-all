package com.thefilipov.brewer.model;

public enum StatusVendaValues {

	ORCAMENTO(1, "Orçamento"), EMITIDA(2, "Emitida"), CANCELADA(3, "Cancelada");

	private Integer codigo;
	private String descricao;

	StatusVendaValues(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static String getDescricaoById(int id) {
		String descricao = null;
		for (StatusVendaValues v : values()) {
			if(v.getCodigo() == id) {
				descricao = v.getDescricao();
				break;
			}
		}
		return descricao;
	}
	
	public static String getDescricaoById(long id) {
		String descricao = null;
		for (StatusVendaValues v : values()) {
			if(v.getCodigo() == id) {
				descricao = v.getDescricao();
				break;
			}
		}
		return descricao;
	}

	public static String getDescricaoById(short id) {
		String descricao = null;
		for (StatusVendaValues v : values()) {
			if(v.getCodigo() == id) {
				descricao = v.getDescricao();
				break;
			}
		}
		return descricao;
	}

	public static String getDescricaoById(byte id) {
		String descricao = null;
		for (StatusVendaValues v : values()) {
			if(v.getCodigo() == id) {
				descricao = v.getDescricao();
				break;
			}
		}
		return descricao;
	}
	
	public static StatusVendaValues parse(int id){
		StatusVendaValues retorno = null;
		for (StatusVendaValues v : values()) {
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
