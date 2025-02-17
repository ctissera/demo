package com.mycompany.demo.converter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import com.mycompany.demo.dto.EmpresaDto;
import com.mycompany.demo.entity.Empresa;

@Service
public class EmpresaConverter extends GenericConverter<EmpresaDto, Empresa>  {
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
	@Override
	public EmpresaDto toDto(Empresa entity) throws IOException {
		//
		EmpresaDto empresaDto = new EmpresaDto();
		//
		empresaDto.setId(entity.getId());
		empresaDto.setCuit(entity.getCuit());
		empresaDto.setRazon_social(entity.getRazon_social());
		String strDate = dateFormat.format(entity.getFecha_adhesion()); 
		empresaDto.setFecha_adhesion(strDate);
		empresaDto.setTelefono(entity.getTelefono());
		//
		return empresaDto;	
	}

	@Override
	public Empresa toEntity(EmpresaDto empresaDto) throws Exception {
		// 
		Empresa empresa = new Empresa();
		//
		empresa.setId(empresaDto.getId());
		empresa.setCuit(empresaDto.getCuit());
		empresa.setRazon_social(empresaDto.getRazon_social());
		
	    String strDate = empresaDto.getFecha_adhesion();  
	    //
	    Date fechaAdhesion = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);  
		empresa.setFecha_adhesion(fechaAdhesion);
		empresa.setTelefono(empresaDto.getTelefono());
		//
		return empresa;
	}
	
	@Override
	public  List<EmpresaDto> toDtoList(List<Empresa> empresaList) throws IOException {
		List<EmpresaDto> empresaDtoList = new ArrayList<>();
		for(Empresa empresa : empresaList) {
			empresaDtoList.add(toDto(empresa));	
		}
		return empresaDtoList;
	}
}