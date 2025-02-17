package com.mycompany.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.demo.dto.EmpresaDto;
import com.mycompany.demo.entity.Empresa;
import com.mycompany.demo.service.EmpresaService;

@Controller
@RestController
@RequestMapping("/itbk")
public class EmpresaController {

	@Resource
	EmpresaService empresaService;

	@RequestMapping(value = "/empresa", method = RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public ResponseEntity<String> createEmpresa(@Valid @RequestBody EmpresaDto empresaDto, HttpServletRequest request, BindingResult bindingResult) throws Exception {
		System.out.println("createEmpresa controller");
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMsg.append(error.getField())
                        .append(": ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }
            return ResponseEntity.badRequest().body(errorMsg.toString());
        }
        
		EmpresaDto empresaDtoSaved = new EmpresaDto(); 
        try {
            empresaDtoSaved = empresaService.createEmpresa(empresaDto);
        }catch (Exception e){
            System.err.println("createBulkEmpresas ERROR : "+e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }		
		
		return new ResponseEntity<>(empresaDtoSaved.getCuit(),HttpStatus.CREATED);
	}

	@RequestMapping(value = "/empresa/import", method = RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	//public List<EmpresaDto> createBulkEmpresas(@RequestBody List<EmpresaDto> empresaDtoList, HttpServletRequest request) throws Exception {
	public ResponseEntity<Integer> createBulkEmpresas(@RequestBody List<EmpresaDto> empresaDtoList, HttpServletRequest request) throws Exception {
		//
		List<EmpresaDto> empresaDtoSaved = new ArrayList<>(); 
				
        try {
            empresaDtoSaved = empresaService.createBulkEmpresas(empresaDtoList);
        }catch (Exception e){
            System.err.println("createBulkEmpresas ERROR : "+e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

		if(empresaDtoSaved.size() == empresaDtoList.size()) {
			return new ResponseEntity<Integer>(empresaDtoSaved.size(), HttpStatus.OK);			
		}
		else {
			return new ResponseEntity<Integer>(-1, HttpStatus.NOT_FOUND);
		}
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
	public ResponseEntity<EmpresaDto>  getEmpresaById(@PathVariable Integer id, HttpServletRequest request) throws Exception {

		if(id == null)
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		EmpresaDto empresaDto = empresaService.getEmpresaById(id);
		
		return new ResponseEntity<>(empresaDto,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/empresa/name/{search}", method = RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public Page<Empresa> getEmpresaByName(@PathVariable String search, Pageable pageable, HttpServletRequest request) throws Exception {
		return empresaService.getEmpresaByName(search, pageable);
	}
	
	@RequestMapping(value = "/empresa", method = RequestMethod.PUT, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	@ResponseBody
	public ResponseEntity<EmpresaDto> updateEmpresa(@Valid @RequestBody EmpresaDto empresaDto, HttpServletRequest request) throws Exception {
		//
		EmpresaDto empresaDtoUpdated = new EmpresaDto(); 
        try {
        	empresaDtoUpdated = empresaService.updateEmpresa(empresaDto);
        }catch (Exception e){
            System.err.println("updateEmpresa ERROR : "+e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
        return new ResponseEntity<>(empresaDtoUpdated,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/empresa/{empresaId}", method = RequestMethod.DELETE, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public ResponseEntity<Integer> deleteEmpresa(@PathVariable Integer empresaId, HttpServletRequest request) throws Exception {
		System.out.println("deleteEmpresa controller");
		Integer returnedValue = 0;
        try {
        	returnedValue = empresaService.deleteEmpresa(empresaId);
        }catch (Exception e){
            System.err.println("delete Empresa ERROR : "+e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }		

		
		if(returnedValue == 0) {
			return new ResponseEntity<Integer>(0, HttpStatus.NOT_FOUND);			
		}
		else {
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		}
		
	}

}
