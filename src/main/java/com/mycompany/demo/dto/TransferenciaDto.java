package com.mycompany.demo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TransferenciaDto {
	
	private Integer id;

	@NotNull(message = "Id de Empresa no puede ser nulo")
	private Integer empresaId;

	@NotNull(message = "La transferencia debe tener un importe")
	@Positive(message = "La transferencia debe un importe mayor a cero")	
	private float importe;
	
	private String fecha_transferencia;
	
	@NotNull(message = "Se debe especificar la cuenta de Debito")
	private String cuenta_debito;

	@NotNull(message = "Se debe especificar la cuenta de Credito")
	private String cuenta_credito;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Integer empresaId) {
		this.empresaId = empresaId;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public String getFecha_transferencia() {
		return fecha_transferencia;
	}

	public void setFecha_transferencia(String fecha_transferencia) {
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
