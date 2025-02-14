package com.mycompany.demo.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.mycompany.demo.dto.EmpresaDto;
import com.mycompany.demo.entity.Empresa;
import com.mycompany.demo.service.EmpresaService;

@RestController
@RequestMapping("/itbk")
public class EmpresaController {
 
	@Resource
	EmpresaService empresaService;

	@RequestMapping(value = "/empresa", method = RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public EmpresaDto createEmpresa(@RequestBody EmpresaDto empresa, HttpServletRequest request) throws Exception {
		return empresaService.createEmpresa(empresa);
	}

	@RequestMapping(value = "/empresa/import", method = RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	//public List<EmpresaDto> createBulkEmpresas(@RequestBody List<EmpresaDto> empresaDtoList, HttpServletRequest request) throws Exception {
	public ResponseEntity<Integer> createBulkEmpresas(@RequestBody List<EmpresaDto> empresaDtoList, HttpServletRequest request) throws Exception {
		
		List<EmpresaDto> empresaDtoSaved = empresaService.createBulkEmpresas(empresaDtoList);
				
		if(empresaDtoSaved.size() == empresaDtoList.size()) {
			return new ResponseEntity<Integer>(empresaDtoSaved.size(), HttpStatus.OK);			
		}
		else {
			return new ResponseEntity<Integer>(-1, HttpStatus.NOT_FOUND);
		}
		//return empresaService.createBulkEmpresas(empresaDtoList);
	}
	
	@RequestMapping(value = "/empresa/all", method = RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public List<EmpresaDto> getEmpresa(HttpServletRequest request) throws Exception {
		return empresaService.getAllEmpresa();
	}	

	@RequestMapping(value = "/empresas/addedlastmonth", method = RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public List<EmpresaDto> getEmpresasAdheredLastMonth(HttpServletRequest request) throws Exception {
		return empresaService.getAllEmpresasAdheredLastMonth();
	}	
	
	@RequestMapping(value = "/empresa/id/{id}", method = RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public Empresa getEmpresaById(@PathVariable Integer id, HttpServletRequest request) throws Exception {
		return empresaService.getEmpresaById(id);
	}
	
	@RequestMapping(value = "/empresa/name/{search}", method = RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public Page<Empresa> getEmpresaByName(@PathVariable String search, Pageable pageable, HttpServletRequest request) throws Exception {
		return empresaService.getEmpresaByName(search, pageable);
	}
	
	@RequestMapping(value = "/empresa", method = RequestMethod.PUT, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public EmpresaDto updateEmpresa(@RequestBody EmpresaDto empresaDto, HttpServletRequest request) throws Exception {
		 
		return empresaService.updateEmpresa(empresaDto);
	}
	
	@RequestMapping(value = "/empresa/{empresaId}", method = RequestMethod.DELETE, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public ResponseEntity<Integer> deleteEmpresa(@PathVariable Integer empresaId, HttpServletRequest request) throws Exception {
		
		Integer returnedValue = empresaService.deleteEmpresa(empresaId);
		
		if(returnedValue == 0) {
			return new ResponseEntity<Integer>(0, HttpStatus.NOT_FOUND);			
		}
		else {
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		}
		
	}

}
