package com.mycompany.demo.controller;

import com.mycompany.demo.dto.EmpresaDto;
import com.mycompany.demo.service.EmpresaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//@WebMvcTest(EmpresaController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmpresaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmpresaService empresaService;

    @InjectMocks
    //@Autowired
    private EmpresaController empresaController;

    private EmpresaDto empresaDto;

    @BeforeEach
    public void setUp() {
    	empresaDto = new EmpresaDto();
    	empresaDto.setRazon_social("Test Empresa");
    	empresaDto.setCuit("20-12345678-1");
    	empresaDto.setFecha_adhesion("2025-01-22");
    	empresaDto.setTelefono("12345678910");
    }

    @Test
    public void testCreateEmpresa() throws Exception {
        // Mock the service layer to return the empresa when saved
        when(empresaService.createEmpresa(empresaDto)).thenReturn(empresaDto);

        // Convert the empresa object to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String empresaJson = objectMapper.writeValueAsString(empresaDto);

        // Perform POST request and verify the response
        mockMvc.perform(post("/itbk/empresa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(empresaJson))
                .andExpect(status().isCreated())
           		.andExpect(content().string("20-12345678-1")
                /*
                .andExpect(jsonPath("$.razon_social").value("Test Empresa"))
                .andExpect(jsonPath("$.cuit").value("20-12345678-1"))
                .andExpect(jsonPath("$.fecha_adhesion").value("2025-01-22"))
                .andExpect(jsonPath("$.telefono").value("12345678910")
                */
                );
    }
}
