package com.program.aconta.domain.enums;

public enum TipoUsuario {

	PESSOAFISICA(1,"Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private Integer cod;
	private String descricao;
	
	private TipoUsuario(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoUsuario toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for (TipoUsuario x : TipoUsuario.values()) {
			if(cod.equals(x.getCod())) 
				return x;
			
		}
		
		throw new IllegalArgumentException("Id invalido: "+cod);
		
	}
}