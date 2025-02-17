package com.mycompany.demo.repository;

import com.mycompany.demo.converter.EmpresaConverter;
import com.mycompany.demo.dto.EmpresaDto;
import com.mycompany.demo.entity.Empresa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class EmpresaRepositoryTest {

    @Autowired
    private EmpresaRepository empresaRepository;

    private EmpresaDto empresaDto;

	private EmpresaConverter empresaConverter;
	
    @BeforeEach
    public void setUp() {
    	empresaDto = new EmpresaDto();
    	empresaDto.setRazon_social("Test Empresa");
    	empresaDto.setCuit("123");
    	empresaDto.setTelefono("5465465");
    	empresaDto.setFecha_adhesion("2025-01-22");
    }

    @Test
    public void testSaveEmpresa() throws Exception {
        //Empresa savedEmpresa = empresaRepository.save(empresaConverter.toEntity(empresaDto));
    	Empresa empresa = new Empresa();
    	Empresa savedEmpresa = empresaRepository.save(empresa);
        assertNotNull(savedEmpresa.getId());
    }
}