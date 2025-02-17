package com.mycompany.demo.service;

import com.mycompany.demo.converter.EmpresaConverter;
import com.mycompany.demo.dto.EmpresaDto;
import com.mycompany.demo.entity.Empresa;
import com.mycompany.demo.repository.EmpresaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import javax.annotation.Resource;

@SpringBootTest
@AutoConfigureMockMvc
public class EmpresaServiceTest {

    @Mock
    private EmpresaRepository empresaRepository;

    //@InjectMocks
    @Resource
    private EmpresaService empresaService;

    @Autowired
	EmpresaConverter empresaConverter;
	
    private EmpresaDto empresaDto;


    @BeforeEach
    public void setUp() {
    	empresaDto = new EmpresaDto();
    	empresaDto.setRazon_social("Test Empresa");
    	empresaDto.setCuit("20-16789789-3");
    	empresaDto.setTelefono("23413414");
    	empresaDto.setFecha_adhesion("2025-01-22");
    }

    @Test
    public void testSaveEmpresa() throws Exception {
        // Mock the repository call to return the empresa object
        when(empresaRepository.save(Mockito.any(Empresa.class))).thenReturn(empresaConverter.toEntity(empresaDto));

        // Call the service method
        EmpresaDto savedEmpresa = empresaService.createEmpresa(empresaDto);

        // Assert that the saved empresa matches
        assertEquals("Test Empresa", savedEmpresa.getRazon_social());
        assertEquals("20-16789789-3", savedEmpresa.getCuit());
        assertEquals("23413414", savedEmpresa.getTelefono());
        assertEquals("2025-01-22", savedEmpresa.getFecha_adhesion());
    }
}