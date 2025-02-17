package com.mycompany.demo.dto;

import java.util.Date;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmpresaDto {
	
	private Integer id;
	
	@NotNull(message = "Cuit no puede ser nulo")
	private String cuit;

	@NotNull(message = "Razon Social no puede ser nulo")
	@Column(name="razon_social", columnDefinition = "varchar2(100)")	
	private String razon_social;

	@NotNull(message = "Fecha de adhesion no puede ser nulo")
	private String fecha_adhesion;

	@NotNull(message = "Telefono debe tener un valor")
    @Size(min = 10, message = "Telefono debe tener un valor de al menos 10 digitos")	
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
