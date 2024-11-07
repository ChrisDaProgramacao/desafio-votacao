package com.example.votacaoapi;

import com.example.votacaoapi.Services.CPFValidationService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CPFValidationServiceTest {

    private final CPFValidationService cpfValidationService = new CPFValidationService();

    @Test
    void testValidCPF() {
        // Exemplos de CPFs válidos
        assertTrue(cpfValidationService.isValidCPF("123.456.789-09"));
        assertTrue(cpfValidationService.isValidCPF("111.444.777-35"));
        assertTrue(cpfValidationService.isValidCPF("529.982.247-25"));
    }

    @Test
    void testInvalidCPF() {
        // Exemplos de CPFs inválidos
        assertFalse(cpfValidationService.isValidCPF("123.456.789-00"));  // CPF com dígito verificador errado
        assertFalse(cpfValidationService.isValidCPF("111.111.111-11"));  // CPF com todos os dígitos iguais
        assertFalse(cpfValidationService.isValidCPF("000.000.000-00"));  // CPF com todos os dígitos iguais
    }

    @Test
    void testInvalidFormatCPF() {
        // Exemplos de CPFs com formato incorreto
        assertFalse(cpfValidationService.isValidCPF("123456789"));      // Menos de 11 dígitos
        assertFalse(cpfValidationService.isValidCPF("123.456.789-000")); // Mais de 11 dígitos
        assertFalse(cpfValidationService.isValidCPF("abc.def.ghi-jk"));  // Letras em vez de números
        assertFalse(cpfValidationService.isValidCPF(""));                // CPF vazio
        assertFalse(cpfValidationService.isValidCPF(null));              // CPF nulo
    }
}
