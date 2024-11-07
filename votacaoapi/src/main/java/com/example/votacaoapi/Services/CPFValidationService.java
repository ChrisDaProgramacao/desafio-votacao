package com.example.votacaoapi.Services;

import org.springframework.stereotype.Service;

@Service
public class CPFValidationService {

    public boolean isValidCPF(String cpf) {
        // Remove todos os caracteres não numéricos
        cpf = cpf.replaceAll("\\D", "");

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais (ex: "11111111111" não é um CPF válido)
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            // Calcula o primeiro dígito verificador
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }
            int firstVerifier = 11 - (sum % 11);
            if (firstVerifier >= 10) firstVerifier = 0;

            // Calcula o segundo dígito verificador
            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            int secondVerifier = 11 - (sum % 11);
            if (secondVerifier >= 10) secondVerifier = 0;

            // Verifica os dígitos verificadores
            return firstVerifier == Character.getNumericValue(cpf.charAt(9)) &&
                    secondVerifier == Character.getNumericValue(cpf.charAt(10));
        } catch (NumberFormatException e) {
            return false;
        }
    }
}