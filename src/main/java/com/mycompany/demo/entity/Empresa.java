package com.mycompany.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table(name="EMPRESAS")
@Entity
@NamedQuery(name="Empresa.findAll", query="SELECT e FROM Empresa e")
public class Empresa implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPRESA_SQ")
    @SequenceGenerator(sequenceName = "EMPRESA_SQ", allocationSize = 1, name = "EMPRESA_SQ")
	private Integer id;

	//CUIT, Razón Social, Fecha Adhesión
	@Column(name="cuit", columnDefinition = "varchar2(13)", nullable = false, length=11)
	private String cuit;
	
	@NotNull(message = "Razon Social no puede ser nulo")
	@Column(name="razon_social", columnDefinition = "varchar2(100)")
	private String razon_social;
	
	@NotNull(message = "Fecha de adhesion no puede ser nulo")
	@Column(name="fecha_adhesion", columnDefinition = "date")
	private Date fecha_adhesion;

	@NotNull(message = "Telefono debe tener un valor")
    @Size(min = 10, message = "Telefono debe tener un valor de al menos 10 digitos")
	@Column(name="telefono", columnDefinition = "varchar2(50)")
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

	public Date getFecha_adhesion() {
		return fecha_adhesion;
	}

	public void setFecha_adhesion(Date fecha_adhesion) {
		this.fecha_adhesion = fecha_adhesion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	

	
}
