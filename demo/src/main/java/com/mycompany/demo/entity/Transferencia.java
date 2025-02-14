package com.mycompany.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Table(name="TRANSFERENCIAS")
@Entity
@NamedQuery(name="Transferencia.findAll", query="SELECT t FROM Transferencia t")
public class Transferencia implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSFERENCIAS_SQ")
    @SequenceGenerator(sequenceName = "TRANSFERENCIAS_SQ", allocationSize = 1, name = "TRANSFERENCIAS_SQ")
	private Integer id;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "empresa_id", nullable=false)
	private Empresa empresa;

	@Column(name="importe", columnDefinition = "number(20,2)")
	private float importe;
	
	@Column(name="fecha_transferencia", columnDefinition = "date")
	private Date fecha_transferencia;
	
	@Column(name="cuenta_debito", columnDefinition = "varchar2(50)")
	private String cuenta_debito;

	@Column(name="cuenta_credito", columnDefinition = "varchar2(50)")
	private String cuenta_credito;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public Date getFecha_transferencia() {
		return fecha_transferencia;
	}

	public void setFecha_transferencia(Date fecha_transferencia) {
		this.fecha_transferencia = fecha_transferencia;
	}

	public String getCuenta_debito() {
		return cuenta_debito;
	}

	public void setCuenta_debito(String cuenta_debito) {
		this.cuenta_debito = cuenta_debito;
	}

	public String getCuenta_credito() {
		return cuenta_credito;
	}

	public void setCuenta_credito(String cuenta_credito) {
		this.cuenta_credito = cuenta_credito;
	}
}