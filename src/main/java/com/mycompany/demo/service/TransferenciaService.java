package com.mycompany.demo.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import com.mycompany.demo.dto.TransferenciaDto;
import com.mycompany.demo.entity.Transferencia;

public interface TransferenciaService {

	List<TransferenciaDto> importTransferencias(List<TransferenciaDto> transferenciaDtoList) throws IOException;

	List<TransferenciaDto> getTransferenciasLastMonth() throws IOException;
	
	List<TransferenciaDto> getAllTransferencia() throws IOException;

	Transferencia getTransferenciaById(Integer transferenciaId);
	
	TransferenciaDto createTransferencia(TransferenciaDto transferenciaDto) throws Exception;
	
	Integer deleteTransferencia(Integer transferenciaId);

	TransferenciaDto updateTransferencia(TransferenciaDto newTransferencia) throws ParseException, IOException, Exception;

	List<TransferenciaDto> getTransferenciasByEmpresa(Integer empresaEmpresaId) throws IOException;
}
