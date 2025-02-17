package com.mycompany.demo.service.validator;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.mycompany.demo.converter.TransferenciaConverter;
import com.mycompany.demo.dto.TransferenciaDto;
import com.mycompany.demo.entity.Empresa;
import com.mycompany.demo.entity.Transferencia;
import com.mycompany.demo.service.TransferenciaService;
import com.mycompany.demo.service.exception.BadRequestException;


@Component
public class EmpresaValidator {

	@Resource
	TransferenciaService transferenciaService;
	
	public void validateFormatCuit(String cuit) {
	    // Validar formato cuit
	    boolean isMatch = Pattern.compile("^([0-9]{2}-[0-9]{8}-[0-9]{1})")
	           .matcher(cuit)
	           .find(); 
	    if(!isMatch) {
	    	throw new BadRequestException("Empresa Validator - Cuit Debe tener el formato 99-99999999-9. "+cuit);
	    }
	}    
	
	public void validateUpdateOrCreateEmpresa(Empresa empresa) {
		validateFormatCuit(empresa.getCuit()); 
		validateCuitEmpresa(empresa);
		validateRazonSocialEmpresa(empresa);
	}
	
	public void validateCuitEmpresa(Empresa empresa) {
		if (empresa.getCuit() == null) {
			throw new BadRequestException("Empresa Validator - Cuit debe tener un valor.");
		}
	}

	public void validateRazonSocialEmpresa(Empresa empresa) {
		if (empresa.getRazon_social() == null || "".equals(empresa.getRazon_social())) {
			throw new BadRequestException("Empresa Validator - Razon Social debe tener un valor.");
		}
	}

	public void validateDeleteEmpresa(Empresa empresa) {
		System.out.println("validateDeleteEmpresa");
		List<TransferenciaDto> transferenciaDtoList = null;
		try {
			transferenciaDtoList = transferenciaService.getTransferenciasByEmpresa(empresa.getId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("transferenciaDtoList.size() = "+transferenciaDtoList.size());
		if ( transferenciaDtoList.size() > 0) {
			throw new BadRequestException("Empresa Validator - La empresa posee movimientos de transferencias.");
		}
	}	
}
