package com.mycompany.demo.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.mycompany.demo.converter.TransferenciaConverter;
import com.mycompany.demo.dto.EmpresaDto;
import com.mycompany.demo.dto.TransferenciaDto;
import com.mycompany.demo.entity.Empresa;
import com.mycompany.demo.entity.Transferencia;
import com.mycompany.demo.repository.TransferenciaRepository;
import com.mycompany.demo.service.exception.BadRequestException;

@Service
public class TransferenciaServiceImpl  implements TransferenciaService  {

	@Resource
	TransferenciaRepository transferenciaRepository;
	
	@Resource
	TransferenciaConverter transferenciaConverter;
	
	@Override
	public List<TransferenciaDto> importTransferencias(List<TransferenciaDto> transferenciaDtoList) throws IOException {
 
		List<TransferenciaDto> transferenciaDtoListRet = new ArrayList<>();
		transferenciaDtoList.stream()
	      .forEach(transferenciaDto -> {
				Transferencia transferencia = null;
				try {
					transferencia = transferenciaConverter.toEntity(transferenciaDto);
					TransferenciaDto transferenciaDtoSaved = transferenciaConverter.toDto(transferenciaRepository.save(transferencia));
					transferenciaDtoListRet.add(transferenciaDtoSaved);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			});
		//
		return transferenciaDtoListRet;
	}

	@Override
	public List<TransferenciaDto> getTransferenciasLastMonth() throws IOException {

		LocalDateTime monthBefore = LocalDateTime.now().minus(Period.ofMonths(1));
		Date monthBeforeDate = Date.from(monthBefore.atZone(ZoneId.systemDefault()).toInstant());

		List<Transferencia> transferenciaList = transferenciaRepository.findAllTransferedLastMonth(monthBeforeDate);
		List<TransferenciaDto> transferenciaDtoList = transferenciaConverter.toDtoList(transferenciaList);
		return transferenciaDtoList;		
	}

	@Override
	public List<TransferenciaDto> getAllTransferencia() throws IOException {
		// 
		List<Transferencia> transferenciaList = transferenciaRepository.findAllTransferencia();
		List<TransferenciaDto> transferenciaDtoList = transferenciaConverter.toDtoList(transferenciaList);
		
		return transferenciaDtoList;
	}

	@Override
	public Transferencia getTransferenciaById(Integer transferenciaId) {
		// 
		return transferenciaRepository.findTransferenciaById(transferenciaId) ;
	}

	@Override
	public TransferenciaDto createTransferencia(TransferenciaDto transferenciaDto) throws Exception {
		// 
		Transferencia transferencia = transferenciaConverter.toEntity(transferenciaDto);
		return transferenciaConverter.toDto(transferenciaRepository.save(transferencia));
	}

	@Override
	public TransferenciaDto updateTransferencia(TransferenciaDto newTransferenciaDto) throws Exception {
		//
		Transferencia newTransferencia = transferenciaConverter.toEntity(newTransferenciaDto);
		
		Transferencia oldTransferencia = transferenciaRepository.findTransferenciaById(newTransferencia.getId());
		if(oldTransferencia == null) {	
				throw new BadRequestException("No existe la transferencia especificada " );			
		}
		//
		Transferencia per = transferenciaRepository.save(oldTransferencia);
		
		return transferenciaConverter.toDto(per);
	}

	@Override
	public Integer deleteTransferencia(Integer transferenciaId) {
		//
		
		Transferencia transferenciaForDelete =  transferenciaRepository.findTransferenciaById(transferenciaId);
		if(transferenciaForDelete == null) {
			return 0;
		}
		else {
			transferenciaRepository.delete(transferenciaForDelete);
		}
		//
		transferenciaForDelete = transferenciaRepository.findTransferenciaById(transferenciaId);
		if(transferenciaForDelete == null) {
			return 1;
		}
		return 0;
		
	}



	
}
