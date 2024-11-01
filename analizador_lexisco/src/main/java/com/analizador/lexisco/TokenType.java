package com.analizador.lexisco;

public enum TokenType {
    PALAVRA_RESERVADA,  // se, senão, int, retorna, etc.
    IDENTIFICADOR,      // Nomes de variáveis, funções, etc.
    NUMERO,             // Constantes numéricas
    OPERADOR,           // +, -, *, /, etc.
    SEPARADOR,          // ;, (), {}, etc.
    STRING,             // Literais de string
    COMENTARIO,         // Comentários
    ESPACO_BRANCO,      // Espaços em branco (ignorado)
    DESCONHECIDO        // Qualquer coisa desconhecida
}
