package com.stocks.oi.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Symbols {
    private static final Map<String, String> symbolMap = new HashMap<>();

    static {
        symbolMap.put("SBIN", "SBIN");
        symbolMap.put("RELIANCE", "RELIANCE");
        symbolMap.put("ICICI", "ICICI");
    }

    public List<String> getAllSymbols() {
        return symbolMap.values().stream().collect(Collectors.toList());
    }
}
