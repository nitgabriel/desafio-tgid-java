package br.nitgabriel.tgid.utils;

public class Validator {

    private static int calculateDigit(String str, int[] weights) {
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += (str.charAt(i) - '0') * weights[i];
        }
        return (sum % 11) < 2 ? 0 : 11 - (sum % 11);
    }

    public static boolean isValidCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.matches(cpf.charAt(0) + "{11}")) {
            return false;
        }

        if (cpf.matches("^(\\d)\\1*$")) {
            return false;
        }

        int[] weights1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] weights2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        int firstDigit = calculateDigit(cpf, weights1);
        int secondDigit = calculateDigit(cpf, weights2);

        return cpf.charAt(9) - '0' == firstDigit && cpf.charAt(10) - '0' == secondDigit;
    }

    public static boolean isValidCNPJ(String cnpj) {
        if (cnpj == null || cnpj.length() != 14 || cnpj.matches(cnpj.charAt(0) + "{14}")) {
            return false;
        }

        if (cnpj.matches("^(\\d)\\1*$")) {
            return false;
        }

        int[] weights1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] weights2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int firstDigit = calculateDigit(cnpj, weights1);
        int secondDigit = calculateDigit(cnpj, weights2);

        return cnpj.charAt(12) - '0' == firstDigit && cnpj.charAt(13) - '0' == secondDigit;
    }
}