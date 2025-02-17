package com.mycompany.demo.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mycompany.demo.dto.EmpresaDto;
import com.mycompany.demo.entity.Empresa;

public interface EmpresaService {

	List<EmpresaDto> getAllEmpresa() throws IOException;

	List<EmpresaDto> getAllEmpresasAdheredLastMonth() throws IOException;
	
	EmpresaDto getEmpresaById(Integer personId);
	
	Page<Empresa> getEmpresaByName(String search, Pageable pageable);
	
	EmpresaDto createEmpresa(EmpresaDto personDto) throws Exception;
	
	List<EmpresaDto> createBulkEmpresas(List<EmpresaDto> empresaDtoList) throws Exception;
	
	Integer deleteEmpresa(Integer personId) throws Exception;

	EmpresaDto updateEmpresa(EmpresaDto newEmpresa) throws ParseException, IOException, Exception;

}
