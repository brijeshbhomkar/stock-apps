package com.stocks.oi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.stocks.oi.document.DailyParticipantOpenInterestDocument;
import com.stocks.oi.document.DailyParticipantOpenInterestDocumentWrapper;
import com.stocks.oi.document.DailyParticipantVolumeDocument;
import com.stocks.oi.document.DailyParticipantVolumeDocumentWrapper;
import com.stocks.oi.mongo.repository.DailyParticipantVolumeRepository;
import com.stocks.oi.response.oi.fii.DailyParticipantDetails;
import com.stocks.oi.util.DateUtil;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Log4j2
@Service
public class DailyParticipantVolumeService {

    private HttpClient client = HttpClient.newHttpClient();

    private String PARTICIPANT_VOLUME = "https://www1.nseindia.com/content/nsccl/fao_participant_vol_";

    @Autowired
    private DailyParticipantVolumeRepository dailyParticipantVolumeRepository;

    @SneakyThrows
    public List<DailyParticipantDetails> getDailyVolume() {
        final String day = DateUtil.getDate(new Date());
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(PARTICIPANT_VOLUME + day + "." + "csv"))
                .header("Accept", "application/csv").build();
        final HttpResponse<InputStream> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofInputStream());
        final InputStream data = httpResponse.body();
        return convertToJson(data);
    }

    @SneakyThrows
    private List<DailyParticipantDetails> convertToJson(final InputStream data) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(data))) {
            List<DailyParticipantDetails> fiis = in
                    .lines()
                    .skip(1)
                    .map(this::mapToOpenInterest)
                    .collect(Collectors.toList());
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            //create the wrapper
            DailyParticipantVolumeDocumentWrapper wrapper = new DailyParticipantVolumeDocumentWrapper();
            fiis.forEach(s -> {
                if (s.getClientType().equalsIgnoreCase("Client")) {
                    wrapper.setClient(getOpenInterestParticipants(s));
                } else if (s.getClientType().equalsIgnoreCase("DII")) {
                    wrapper.setDii(getOpenInterestParticipants(s));
                } else if (s.getClientType().equalsIgnoreCase("FII")) {
                    wrapper.setFii(getOpenInterestParticipants(s));
                } else if (s.getClientType().equalsIgnoreCase("Pro")) {
                    wrapper.setPro(getOpenInterestParticipants(s));
                } else if (s.getClientType().equalsIgnoreCase("TOTAL")) {
                    wrapper.setTotal(getOpenInterestParticipants(s));
                }
            });
            wrapper.setDate(DateUtil.getDate(new Date()));
            dailyParticipantVolumeRepository.save(wrapper);
            return fiis;
        }
    }

    private DailyParticipantVolumeDocument getOpenInterestParticipants(final DailyParticipantDetails participantDetails) {
        DailyParticipantVolumeDocument volumeDocument = new DailyParticipantVolumeDocument();
        volumeDocument.setClientType(participantDetails.getClientType());
        volumeDocument.setFutureIndexLong(Long.parseLong(participantDetails.getFutureIndexLong()));
        volumeDocument.setFutureIndexShort(Long.parseLong(participantDetails.getFutureIndexShort()));
        volumeDocument.setFutureStockLong(Long.parseLong(participantDetails.getFutureStockLong()));
        volumeDocument.setFutureStockShort(Long.parseLong(participantDetails.getFutureStockShort()));
        volumeDocument.setOptionIndexCallLong(Long.parseLong(participantDetails.getOptionIndexCallLong()));
        volumeDocument.setOptionIndexCallShort(Long.parseLong(participantDetails.getOptionIndexCallShort()));
        volumeDocument.setOptionIndexPutLong(Long.parseLong(participantDetails.getOptionIndexPutLong()));
        volumeDocument.setOptionIndexPutShort(Long.parseLong(participantDetails.getOptionIndexPutShort()));
        volumeDocument.setOptionStockCallLong(Long.parseLong(participantDetails.getOptionStockCallLong()));
        volumeDocument.setOptionStockCallShort(Long.parseLong(participantDetails.getOptionStockCallShort()));
        volumeDocument.setOptionStockPutLong(Long.parseLong(participantDetails.getOptionStockPutLong()));
        volumeDocument.setOptionStockPutShort(Long.parseLong(participantDetails.getOptionStockPutShort()));
        volumeDocument.setTotalLongContracts(Long.parseLong(participantDetails.getTotalLongContracts()));
        volumeDocument.setTotalShortContracts(Long.parseLong(participantDetails.getTotalShortContracts()));
        return volumeDocument;

    }

    private <R> DailyParticipantDetails mapToOpenInterest(String line) {
        Pattern pattern = Pattern.compile(",");
        String[] x = pattern.split(line);
        return new DailyParticipantDetails(x[0],
                x[1],
                x[2],
                x[3],
                x[4],
                x[5],
                x[6],
                x[7],
                x[8],
                x[9],
                x[10],
                x[11],
                x[12],
                x[13],
                x[14]
        );
    }
}
