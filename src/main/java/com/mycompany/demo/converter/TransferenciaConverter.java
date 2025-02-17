package com.mycompany.demo.converter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.demo.dto.EmpresaDto;
import com.mycompany.demo.dto.TransferenciaDto;
import com.mycompany.demo.entity.Empresa;
import com.mycompany.demo.entity.Transferencia;
import com.mycompany.demo.service.EmpresaService;

@Service
public class TransferenciaConverter extends GenericConverter<TransferenciaDto, Transferencia>  {
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
	
	@Resource
	EmpresaService empresaService;

	@Resource
	EmpresaConverter empresaConverter;
	
	@Override
	public TransferenciaDto toDto(Transferencia entity) throws IOException {
		
		TransferenciaDto transferenciaDto = new TransferenciaDto();
		//
		transferenciaDto.setId(entity.getId());
		transferenciaDto.setEmpresaId(entity.getEmpresa().getId());
		transferenciaDto.setImporte(entity.getImporte());
		transferenciaDto.setCuenta_credito(entity.getCuenta_credito());
		transferenciaDto.setCuenta_debito(entity.getCuenta_debito());
		String strDate = dateFormat.format(entity.getFecha_transferencia()); 
		transferenciaDto.setFecha_transferencia(strDate);
		transferenciaDto.setImporte(entity.getImporte());
		//
		return transferenciaDto;	
	}

	@Override
	public Transferencia toEntity(TransferenciaDto transferenciaDto) throws Exception {
		// 
		Transferencia transferencia = new Transferencia();
		transferencia.setId(transferenciaDto.getId());
		Integer empresaId = transferenciaDto.getEmpresaId();
		
		EmpresaDto empresaDto = empresaService.getEmpresaById(empresaId);
		
		transferencia.setImporte(transferenciaDto.getImporte());
		transferencia.setEmpresa( empresaConverter.toEntity(empresaDto));
		transferencia.setCuenta_credito(transferenciaDto.getCuenta_credito());
		transferencia.setCuenta_debito(transferenciaDto.getCuenta_debito());
		
	    String strDate = transferenciaDto.getFecha_transferencia();  
	    System.out.println("strDate:"+strDate);
	    Date fechaTransferencia = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);  
		transferencia.setFecha_transferencia(fechaTransferencia);
		
		return transferencia;
	}
	
	@Override
	public  List<TransferenciaDto> toDtoList(List<Transferencia> transferenciaList) throws IOException {
		List<TransferenciaDto> transferenciaDtoList = new ArrayList<>();
		for(Transferencia transferencia : transferenciaList) {
			transferenciaDtoList.add(toDto(transferencia));	
		}
		return transferenciaDtoList;
	}
	

}
