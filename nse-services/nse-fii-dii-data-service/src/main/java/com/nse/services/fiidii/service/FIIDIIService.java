package com.nse.services.fiidii.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nse.services.fiidii.entity.FiiDiiParticipantEntity;
import com.nse.services.fiidii.model.FiiDiiParticipant;
import com.nse.services.fiidii.repository.FIIDIIRepository;
import com.nse.services.fiidii.util.DateUtil;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Log4j2
@Service
public class FIIDIIService {

    @Autowired
    private FIIDIIRepository fiidiiRepository;

    private HttpClient client = HttpClient.newHttpClient();

    private String FII_URL = "https://www1.nseindia.com/content/nsccl/fao_participant_oi_";

    private String FILE_TYPE = "csv";

    public Map<Date, List<FiiDiiParticipant>> getFiiDiiParticipantsDaily() {
        Map<Date, List<FiiDiiParticipant>> fiidiiMap = getFII(getDaily().stream().map(this::localDateToDate).collect(Collectors.toList()));
        if (!CollectionUtils.isEmpty(fiidiiMap)) {
            List<List<FiiDiiParticipantEntity>> fiiDiiParticipantEntities = fiidiiMap.entrySet().stream().map(this::participantMapper).collect(Collectors.toList());
            fiiDiiParticipantEntities.stream().flatMap(List::stream).forEach(s -> fiidiiRepository.save(s));
        }
        return fiidiiMap;
    }

    public Map<Date, List<FiiDiiParticipant>> getFiiDiiParticipantsWeekly() {
        Map<Date, List<FiiDiiParticipant>> fiidiiMap = getFII(getWeekly().stream().map(this::localDateToDate).collect(Collectors.toList()));
        if (!CollectionUtils.isEmpty(fiidiiMap)) {
            List<List<FiiDiiParticipantEntity>> fiiDiiParticipantEntities = fiidiiMap.entrySet().stream().map(this::participantMapper).collect(Collectors.toList());
            fiiDiiParticipantEntities.stream().flatMap(List::stream).forEach(s -> fiidiiRepository.save(s));
        }
        return fiidiiMap;
    }

    public Map<Date, List<FiiDiiParticipant>> getFiiDiiParticipantsMonthly() {
        Map<Date, List<FiiDiiParticipant>> fiidiiMap = getFII(getMonthly().stream().map(this::localDateToDate).collect(Collectors.toList()));
        if (!CollectionUtils.isEmpty(fiidiiMap)) {
            List<List<FiiDiiParticipantEntity>> fiiDiiParticipantEntities = fiidiiMap.entrySet().stream().map(this::participantMapper).collect(Collectors.toList());
            fiiDiiParticipantEntities.stream().flatMap(List::stream).forEach(s -> fiidiiRepository.save(s));
        }
        return fiidiiMap;
    }

    private <R> List<FiiDiiParticipantEntity> participantMapper(Map.Entry<Date, List<FiiDiiParticipant>> dateListEntry) {
        Date date = dateListEntry.getKey();
        List<FiiDiiParticipant> fiiDiiParticipants = dateListEntry.getValue();
        List<FiiDiiParticipantEntity> fiiDiiParticipantEntities = new ArrayList<>();
        fiiDiiParticipants.stream().skip(1).forEach(fiiDiiParticipant -> {
            FiiDiiParticipantEntity fiiDiiParticipantEntity = new FiiDiiParticipantEntity();
            fiiDiiParticipantEntity.setCreatedDate(date);
            fiiDiiParticipantEntity.setClientType(fiiDiiParticipant.getClientType());
            fiiDiiParticipantEntity.setFutureIndexLong(fiiDiiParticipant.getFutureIndexLong());
            fiiDiiParticipantEntity.setFutureIndexShort(fiiDiiParticipant.getFutureIndexShort());
            fiiDiiParticipantEntity.setFutureStockLong(fiiDiiParticipant.getFutureStockLong());
            fiiDiiParticipantEntity.setFutureStockShort(fiiDiiParticipant.getFutureStockShort());
            fiiDiiParticipantEntity.setOptionIndexCallLong(fiiDiiParticipant.getOptionIndexCallLong());
            fiiDiiParticipantEntity.setOptionIndexCallShort(fiiDiiParticipant.getOptionIndexCallShort());
            fiiDiiParticipantEntity.setOptionIndexPutLong(fiiDiiParticipant.getOptionIndexPutLong());
            fiiDiiParticipantEntity.setOptionIndexPutShort(fiiDiiParticipant.getOptionIndexPutShort());
            fiiDiiParticipantEntity.setOptionStockCallLong(fiiDiiParticipant.getOptionStockCallLong());
            fiiDiiParticipantEntity.setOptionStockCallShort(fiiDiiParticipant.getOptionStockCallShort());
            fiiDiiParticipantEntity.setOptionStockPutLong(fiiDiiParticipant.getOptionStockPutLong());
            fiiDiiParticipantEntity.setOptionStockPutShort(fiiDiiParticipant.getOptionStockPutShort());
            fiiDiiParticipantEntity.setTotalLongContracts(fiiDiiParticipant.getTotalLongContracts());
            fiiDiiParticipantEntity.setTotalShortContracts(fiiDiiParticipant.getTotalShortContracts());
            fiiDiiParticipantEntities.add(fiiDiiParticipantEntity);
        });
        return fiiDiiParticipantEntities;
    }



    private List<LocalDate> getDaily() {
        List<LocalDate> localDateList = new ArrayList<>();
        LocalDate localDate = LocalDate.now(); //today's date
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        if (DayOfWeek.SUNDAY == dayOfWeek) {
            localDate = localDate.minusDays(2);
        } else if (DayOfWeek.SATURDAY == dayOfWeek) {
            localDate = localDate.minusDays(1);
        }
        localDateList.add(localDate);
        return localDateList;
    }

    private List<LocalDate> getWeekly() {
        List<LocalDate> localDateList = new ArrayList<>();
        int count = 1;
        localDateList.add(LocalDate.now());
        while (count <= 7) {
            LocalDate date = LocalDate.now();
            date = date.minusDays(count);
            localDateList.add(date);
            count++;
        }
        return localDateList.stream().filter(s -> s.getDayOfWeek() != DayOfWeek.SATURDAY)
                .filter(k -> k.getDayOfWeek() != DayOfWeek.SUNDAY).collect(Collectors.toList());
    }

    private List<LocalDate> getMonthly() {
        List<LocalDate> localDateList = new ArrayList<>();
        int count = 1;
        localDateList.add(LocalDate.now());
        while (count <= 30) {
            LocalDate date = LocalDate.now();
            date = date.minusDays(count);
            localDateList.add(date);
            count++;
        }
        return localDateList.stream().filter(s -> s.getDayOfWeek() != DayOfWeek.SATURDAY)
                .filter(k -> k.getDayOfWeek() != DayOfWeek.SUNDAY).collect(Collectors.toList());
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    private <R> Date localDateToDate(LocalDate localDate) {
        return asDate(localDate);
    }

    @SneakyThrows
    public Map<Date, List<FiiDiiParticipant>> getFII(final List<Date> dates) {
        Map<Date, List<FiiDiiParticipant>> fiidiimap = new HashMap<>();
        dates.stream().forEach(s -> {
            try {
                final String day = DateUtil.getDate(s);
                HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(FII_URL + day + "." + FILE_TYPE))
                        .header("Accept", "application/csv").build();
                final HttpResponse<InputStream> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofInputStream());
                final InputStream data = httpResponse.body();
                fiidiimap.put(s, convertToJson(data));
            } catch (Exception e) {
                //e.printStackTrace();
            }
        });
        return fiidiimap;
    }

    @SneakyThrows
    private List<FiiDiiParticipant> convertToJson(final InputStream data) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(data))) {
            List<FiiDiiParticipant> fiis = in
                    .lines()
                    .skip(1)
                    .map(this::mapper)
                    .collect(Collectors.toList());
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return fiis;
        }
    }

    private <R> FiiDiiParticipant mapper(String line) {
        Pattern pattern = Pattern.compile(",");
        String[] x = pattern.split(line);
        return new FiiDiiParticipant(x[0],
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

    public Map<Date, List<FiiDiiParticipant>> getFiiDiiParticipantForXdays(String days) {
        Map<Date, List<FiiDiiParticipant>> fiidiiMap = getFII(getXdays(days).stream().map(this::localDateToDate).collect(Collectors.toList()));
        if (!CollectionUtils.isEmpty(fiidiiMap)) {
            List<List<FiiDiiParticipantEntity>> fiiDiiParticipantEntities = fiidiiMap.entrySet().stream().map(this::participantMapper).collect(Collectors.toList());
            fiiDiiParticipantEntities.stream().flatMap(List::stream).forEach(s -> fiidiiRepository.save(s));
        }
        return fiidiiMap;
    }

    private List<LocalDate> getXdays(String days) {
        List<LocalDate> localDateList = new ArrayList<>();
        int noOfdays = Integer.parseInt(days);
        int count = 1;
        localDateList.add(LocalDate.now());
        while (count <= noOfdays) {
            LocalDate date = LocalDate.now();
            date = date.minusDays(count);
            localDateList.add(date);
            count++;
        }
        return localDateList.stream().filter(s -> s.getDayOfWeek() != DayOfWeek.SATURDAY)
                .filter(k -> k.getDayOfWeek() != DayOfWeek.SUNDAY).collect(Collectors.toList());
    }

    public List<Map<Date, FiiDiiParticipant>> getParticipantByDate(final String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<FiiDiiParticipantEntity> dateSpecificFiiDiiParticipants = fiidiiRepository.getByCreatedDate(asDate(localDate));
        if (!CollectionUtils.isEmpty(dateSpecificFiiDiiParticipants)) {
            return dateSpecificFiiDiiParticipants.stream().filter(s -> s.getClientType().equalsIgnoreCase("FII")).map(this::convertJsonToEnity).collect(Collectors.toList());
        }
        return null;
    }

    public List<Map<Date, FiiDiiParticipant>> getParticipantsByFii(final int days) {
        List<FiiDiiParticipantEntity> fiiDiiParticipantEntities = fiidiiRepository.findAll();
        List<FiiDiiParticipantEntity> sorted = fiiDiiParticipantEntities.stream().filter(s -> s.getClientType().equalsIgnoreCase("FII")).sorted(Comparator.comparing(FiiDiiParticipantEntity::getCreatedDate).reversed()).limit(days).collect(Collectors.toList());
        return sorted.stream().sorted(Comparator.comparing(FiiDiiParticipantEntity::getCreatedDate)).map(this::convertJsonToEnity).collect(Collectors.toList());
    }

    private <R> Map<Date, FiiDiiParticipant> convertJsonToEnity(final FiiDiiParticipantEntity fiiDiiParticipantEntity) {
        final Map<Date, FiiDiiParticipant> dataFiiDiiParticipantMap = new HashMap<>();
        final FiiDiiParticipant fiiDiiParticipant = new FiiDiiParticipant();
        fiiDiiParticipant.setClientType(fiiDiiParticipantEntity.getClientType());
        fiiDiiParticipant.setFutureIndexLong(fiiDiiParticipantEntity.getFutureIndexLong());
        fiiDiiParticipant.setFutureIndexShort(fiiDiiParticipantEntity.getFutureIndexShort());
        fiiDiiParticipant.setFutureStockLong(fiiDiiParticipantEntity.getFutureStockLong());
        fiiDiiParticipant.setFutureStockShort(fiiDiiParticipantEntity.getFutureStockShort());
        fiiDiiParticipant.setOptionIndexCallLong(fiiDiiParticipantEntity.getOptionIndexCallLong());
        fiiDiiParticipant.setOptionIndexCallShort(fiiDiiParticipantEntity.getOptionIndexCallShort());
        fiiDiiParticipant.setOptionIndexPutLong(fiiDiiParticipantEntity.getOptionIndexPutLong());
        fiiDiiParticipant.setOptionIndexPutShort(fiiDiiParticipantEntity.getOptionIndexPutShort());
        fiiDiiParticipant.setOptionStockCallLong(fiiDiiParticipantEntity.getOptionStockCallLong());
        fiiDiiParticipant.setOptionStockCallShort(fiiDiiParticipantEntity.getOptionStockCallShort());
        fiiDiiParticipant.setOptionStockPutLong(fiiDiiParticipantEntity.getOptionStockPutLong());
        fiiDiiParticipant.setOptionStockPutShort(fiiDiiParticipantEntity.getOptionStockPutShort());
        fiiDiiParticipant.setTotalLongContracts(fiiDiiParticipantEntity.getTotalLongContracts());
        fiiDiiParticipant.setTotalShortContracts(fiiDiiParticipantEntity.getTotalShortContracts());
        dataFiiDiiParticipantMap.put(fiiDiiParticipantEntity.getCreatedDate(), fiiDiiParticipant);
        return dataFiiDiiParticipantMap;
    }
}
