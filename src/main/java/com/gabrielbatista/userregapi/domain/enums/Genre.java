package com.gabrielbatista.userregapi.domain.enums;

/**
 *  Generos que podem ser utilizados
 */
public enum Genre {
	
	MALE(1,"Male"),
	FEMININE(2,"Feminine"),
	UNDEFINED(3,"Undefined");
	
	private Integer cod;
	private String desc;
	
	private Genre(Integer cod,String desc) {
		this.cod = cod;
		this.desc = desc;
	}	

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static Genre toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for (Genre x:Genre.values()) {
			if (x.cod.equals(cod)) {
				return x;
			}
		}		
		throw new IllegalArgumentException("Id Inválido: " + cod);
	}	
	
	public static Genre fromDesctoEnum(String desc) {
		
		Integer id = null;
		
		if (desc.equals("MALE")) {
			id = 1;
		} else if (desc.equals("FEMALE")) {
			id = 2;
		} else if (desc.equals("UNDEFINED")) {
			id = 3;
		}
		if(id == null) {
			return null;
		}
		
		for (Genre x:Genre.values()) {
			if (x.cod.equals(id)) {
				return x;
			}
		}		
		throw new IllegalArgumentException("Invalid Id: " + id);
	}	
	
	public static Integer toInteger(String desc) {
		
		if(desc == null) {
			return null;
		}
		
		for (Genre x:Genre.values()) {
			if (x.desc.equals(desc)) {
				return x.cod;
			}
		}		
		throw new IllegalArgumentException("Tipo Inválido: " + desc);
	}
}
