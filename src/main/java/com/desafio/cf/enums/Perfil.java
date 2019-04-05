package com.desafio.cf.enums;

public enum Perfil {

	ADMIN(1, "ROLE_ADMIN", "admin"),
	CLIENTE(2, "ROLE_CLIENTE", "cliente");
	
	private Integer cod;
	private String role;
	private String desc;
	
	private Perfil(Integer cod, String role, String desc) {
		this.cod = cod;
		this.role = role;
		this.desc = desc;
	}
	
	public Integer getCod() {
		return cod;
	}
	
	public String getRole() {
		return role;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public static String toDesc(String role) {
		if(role == null) {
			return null;
		}
		for (Perfil perfil : Perfil.values()) {
			if(perfil.getRole().equals(role)) {
				return perfil.getDesc();
			}
		}
		throw new IllegalArgumentException("Role inválida: " + role);
	}
	
	public static Perfil toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (Perfil x : Perfil.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
