package com.connector.sgx.nifty;

import java.util.Optional;

@FunctionalInterface
public interface ISGXNiftyOptionConnector {

    public static final String BASE_URL = "https://api.sgx.com/derivatives/v1.0/history?category=";

    public Optional<String> connect(final String marketType);

    static String endpoint(final String marketType) {
        return BASE_URL + marketType;
    }

}
