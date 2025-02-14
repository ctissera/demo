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

import com.mycompany.demo.dto.TransferenciaDto;
import com.mycompany.demo.entity.Transferencia;
import com.mycompany.demo.service.TransferenciaService;

@RestController
@RequestMapping("/itbk")
public class TransferenciaController {

	@Resource
	TransferenciaService transferenciaService;

	@RequestMapping(value = "/transferencia/import", method = RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public  List<TransferenciaDto> importTransferencias(@RequestBody List<TransferenciaDto> transferenciaDtoList, HttpServletRequest request) throws Exception {
		 
		return transferenciaService.importTransferencias(transferenciaDtoList);
	}
	
	@RequestMapping(value = "/transferencia/lastmonth", method = RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public List<TransferenciaDto> getTransferenciasLastMonth(HttpServletRequest request) throws Exception {
		 
		return transferenciaService.getTransferenciasLastMonth();
	}	

	@RequestMapping(value = "/transferencia", method = RequestMethod.POST, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public TransferenciaDto createTransferencia(@RequestBody TransferenciaDto transferencia, HttpServletRequest request) throws Exception {
		 
		return transferenciaService.createTransferencia(transferencia);
	}

	@RequestMapping(value = "/transferencia/all", method = RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public List<TransferenciaDto> getTransferencia(HttpServletRequest request) throws Exception {
		 
		return transferenciaService.getAllTransferencia();
	}	
	
	@RequestMapping(value = "/transferencia/id/{id}", method = RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE + "; charset=UTF-8"})
	public Transferencia getTransferenciaById(@PathVariable Integer id, HttpServletRequest request) throws Exception {
		 
		return transferenciaService.getTransferenciaById(id);
	}
	
}
