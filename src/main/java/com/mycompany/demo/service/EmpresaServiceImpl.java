package com.mycompany.demo.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.mycompany.demo.converter.EmpresaConverter;
import com.mycompany.demo.dto.EmpresaDto;
import com.mycompany.demo.entity.Empresa;
import com.mycompany.demo.repository.EmpresaRepository;
import com.mycompany.demo.service.exception.BadRequestException;

@Service
public class EmpresaServiceImpl  implements EmpresaService  {

	@Resource
	//@Autowired
	EmpresaRepository empresaRepository;
	
	@Resource
	EmpresaConverter empresaConverter;

	@Override
	public EmpresaDto createEmpresa(EmpresaDto empresaDto) throws Exception {
		// 
		if(empresaDto.getCuit() != null ) {
			Empresa oldEmpresa = empresaRepository.findEmpresaByCuit(empresaDto.getCuit());
			if(oldEmpresa != null) {	
					throw new BadRequestException("Ya existe una empresaa con el CUIT especificado " );			
			}
		}
		//
		Empresa empresa = empresaConverter.toEntity(empresaDto);
		return empresaConverter.toDto(empresaRepository.save(empresa));
	}

	@Override
	public List<EmpresaDto> getAllEmpresa() throws IOException {
		// 
		List<Empresa> empresaList = empresaRepository.findAllEmpresa();
		List<EmpresaDto> empresaDtoList = empresaConverter.toDtoList(empresaList);
		
		return empresaDtoList;
	}

	@Override
	public Empresa getEmpresaById(Integer empresaId) {
		// 
		return empresaRepository.findEmpresaById(empresaId) ;
	}

	@Override
	public Page<Empresa> getEmpresaByName(String search, Pageable pageable) {
		//
		return  empresaRepository.findEmpresaByName(search, pageable) ;
	}

	@Override
	public EmpresaDto updateEmpresa(EmpresaDto newEmpresaDto) throws Exception {
		//
		Empresa newEmpresa = empresaConverter.toEntity(newEmpresaDto);
		
		Empresa oldEmpresa = empresaRepository.findEmpresaById(newEmpresa.getId());
		if(oldEmpresa == null) {	
				throw new BadRequestException("No existe la empresa especificada " );			
		}
		//
		if(!oldEmpresa.getTelefono().equals(newEmpresa.getTelefono()) ) {
			oldEmpresa.setTelefono(newEmpresa.getTelefono());
		}
		//
		Empresa per = empresaRepository.save(oldEmpresa);
		
		return empresaConverter.toDto(per);
	}

	@Override
	public Integer deleteEmpresa(Integer empresaId) {
		//
		
		Empresa empresaForDelete =  empresaRepository.findEmpresaById(empresaId);
		if(empresaForDelete == null) {
			return 0;
		}
		else {
			empresaRepository.delete(empresaForDelete);
		}
		//
		empresaForDelete = empresaRepository.findEmpresaById(empresaId);
		if(empresaForDelete == null) {
			return 1;
		}
		return 0;
		
	}

	@Override
	public List<EmpresaDto> createBulkEmpresas(List<EmpresaDto> empresaDtoList) throws Exception {
		// 
		empresaDtoList.stream()
	      .forEach(empresaDto -> {
				if(empresaDto.getCuit() != null ) {
					Empresa oldEmpresa = empresaRepository.findEmpresaByCuit(empresaDto.getCuit());
					if(oldEmpresa != null && oldEmpresa.getCuit() != null) {
						throw new BadRequestException("Ya existe una empresa con el CUIT especificado");			
					}
				}
				Empresa empresa;
				try {
					empresa = empresaConverter.toEntity(empresaDto);
					empresaConverter.toDto(empresaRepository.save(empresa));
				} catch (Exception e) {
					e.printStackTrace();
				}
	      });
		  
		//
		return empresaDtoList;
	}

	@Override
	public List<EmpresaDto> getAllEmpresasAdheredLastMonth() throws IOException {
		// 
		LocalDateTime monthBefore = LocalDateTime.now().minus(Period.ofMonths(1));
		Date monthBeforeDate = Date.from(monthBefore.atZone(ZoneId.systemDefault()).toInstant());
		
		List<Empresa> empresaList = empresaRepository.findAllEmpresasAdheredLastMonth(monthBeforeDate);
		List<EmpresaDto> empresaDtoList = empresaConverter.toDtoList(empresaList);
		return empresaDtoList;
	}	
}
