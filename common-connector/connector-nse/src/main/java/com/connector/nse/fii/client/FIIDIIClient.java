package com.connector.nse.fii.client;

import com.connector.common.util.ApplicationClient;
import com.connector.common.util.Endpoint;
import com.connector.nse.common.ICommonClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Log4j2
@Component
public class FIIDIIClient implements ICommonClient {

    @Override
    public String caller1(final String endpoint, final String symbol) throws IOException, InterruptedException {
        return null;
    }

    @Override
    public InputStream caller2(final String endpoint, final String dateFormat, final String fileType) throws IOException, InterruptedException {
        final String url = Endpoint.valueOfLabel(endpoint).getEndpoint() + dateFormat + "." + fileType;
        log.info("Fetching data for url : " + url);
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url))
                .header("Accept", "application/csv").build();
        final HttpResponse<InputStream> httpResponse = ApplicationClient.getClient().send(httpRequest, HttpResponse.BodyHandlers.ofInputStream());
        return httpResponse.body();
    }

    @Override
    public String callerFallback(RuntimeException exception, String endpoint, final String symbol) {
        return null;
    }
}
