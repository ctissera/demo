package com.mycompany.demo.dto;

import java.util.Date;

public class EmpresaDto {
	
	private Integer id;
	
	private String cuit;

	private String razon_social;

	private String fecha_adhesion;

	private String telefono;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}


	public String getRazon_social() {
		return razon_social;
	}

	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}

	public String getFecha_adhesion() {
		return fecha_adhesion;
	}

	public void setFecha_adhesion(String fecha_adhesion) {
		this.fecha_adhesion = fecha_adhesion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	
}
