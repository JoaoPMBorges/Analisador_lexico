package com.analizador.lexisco;

import java.util.LinkedHashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, Integer> symbols;
    private int currentId;

    public SymbolTable() {
        this.symbols = new LinkedHashMap<>();
        this.currentId = 1;
    }

    public int addSymbol(String name) {
        if (!symbols.containsKey(name)) {
            symbols.put(name, currentId);
            return currentId++;
        }
        return symbols.get(name);
    }

    public int getSymbolId(String name) {
        return symbols.getOrDefault(name, -1);
    }

    public void printSymbolTable() {
        for (Map.Entry<String, Integer> entry : symbols.entrySet()) {
            System.out.println(entry.getValue() + ": " + entry.getKey());
        }
    }
}


