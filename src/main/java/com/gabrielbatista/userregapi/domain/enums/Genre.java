package com.gabrielbatista.userregapi.domain.enums;


public enum Genre {
	
	MALE(1,"M","Male"),
	FEMININE(2,"F","Feminine"),
	UNDEFINED(3,"U","Undefined");
	
	private Integer id;
	private String cod;
	private String desc;
	
	private Genre(Integer id, String cod, String desc) {
		this.id = id;
		this.cod = cod;
		this.desc = desc;
	}	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}	
	
	public static Genre toEnum(String cod) {
		
		Integer id = null;
		
		if (cod.equals("M")) {
			id = 1;
		} else if (cod.equals("F")) {
			id = 2;
		} else if (cod.equals("U")) {
			id = 3;
		}
		if(id == null) {
			return null;
		}
		
		for (Genre x:Genre.values()) {
			if (x.id.equals(id)) {
				return x;
			}
		}		
		throw new IllegalArgumentException("Invalid Id: " + id);
	}	
	
	public static Genre toInteger(Integer id) {
		
		if(id == null) {
			return null;
		}
		
		for (Genre x:Genre.values()) {
			if (x.id.equals(id)) {
				return x;
			}
		}		
		throw new IllegalArgumentException("Invalid cod: " + id);
	}
}
