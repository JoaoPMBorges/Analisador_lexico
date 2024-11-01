package com.analizador.lexisco;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String fileName = "C:/Users/motab/OneDrive/Documentos/AnalizadorLexisco/analizador_lexisco/src/main/java/com/analizador/lexisco/arquivoASerAnalizado/codigo.c";

        try {
            // Ler o arquivo
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            StringBuilder code = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                code.append(line).append("\n");
            }

            reader.close();

            Lexer lexer = new Lexer(code.toString());
            lexer.tokenize();

            System.out.println("Tokens:");
            for (String token : lexer.getTokens()) {
                System.out.println(token);
            }

            System.out.println("\nTabela de Símbolos:");
            lexer.getSymbolTable().printSymbolTable();

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
