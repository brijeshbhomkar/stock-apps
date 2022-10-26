package com.nse.services.participant.service;

import com.connector.nse.participant.client.ParticipantClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nse.services.participant.entity.ParticipantEntity;
import com.nse.services.participant.model.Participant;
import com.nse.services.participant.repository.ParticipantRepository;
import com.nse.services.participant.util.DateUtil;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * The Participant service to get the list of market participants from the nse based on different criteria's.
 */
@Log4j2
@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private ParticipantClient participantClient;

    /**
     * Get the dii participants in the market.
     *
     * @param day The day on which dii data is retrieved.
     * @return The Map based on the date with list of participants
     */
    public Map<Date, List<Participant>> getDiiParticipantByDate(final String day) {
        List<LocalDate> dates = new ArrayList<>();
        dates.add(LocalDate.parse(day));
        Map<Date, List<Participant>> diiParticipantsMap = getParticipants(dates.stream().map(this::localDateToDate).collect(Collectors.toList()));
        if (!CollectionUtils.isEmpty(diiParticipantsMap)) {
            List<List<ParticipantEntity>> participantEntities = diiParticipantsMap.entrySet().stream().map(this::participantMapper).collect(Collectors.toList());
            participantEntities.stream().flatMap(List::stream).forEach(s ->
                    participantRepository.save(s));
        }
        return diiParticipantsMap;
    }

    /**
     * Get all the participants in the market daily.
     *
     * @return The Daily all participants in the market.
     */
    public Map<Date, List<Participant>> getDailyParticipants() {
        Map<Date, List<Participant>> allParticipantsDaily = getParticipants(getDaily().stream().map(this::localDateToDate).collect(Collectors.toList()));
        if (!CollectionUtils.isEmpty(allParticipantsDaily)) {
            List<List<ParticipantEntity>> participantsEntities = allParticipantsDaily.entrySet().stream().map(this::participantMapper).collect(Collectors.toList());
            participantsEntities.stream().flatMap(List::stream).forEach(s ->
                    participantRepository.save(s));
        }
        return allParticipantsDaily;
    }

    /**
     * Get all the participants in the market weekly.
     *
     * @return The Weekly all participants in the market.
     */
    public Map<Date, List<Participant>> getWeeklyParticipants() {
        Map<Date, List<Participant>> allParticipantsWeekly = getParticipants(getWeekly().stream().map(this::localDateToDate).collect(Collectors.toList()));
        if (!CollectionUtils.isEmpty(allParticipantsWeekly)) {
            List<List<ParticipantEntity>> participantEntities = allParticipantsWeekly.entrySet().stream().map(this::participantMapper).collect(Collectors.toList());
            participantEntities.stream().flatMap(List::stream).forEach(s -> participantRepository.save(s));
        }
        return allParticipantsWeekly;
    }

    /**
     * Get all the participants in the market monthly.
     *
     * @return The monthly all participants in the market.
     */
    public Map<Date, List<Participant>> getMonthlyParticipants() {
        Map<Date, List<Participant>> allParticipantsMonthly = getParticipants(getMonthly().stream().map(this::localDateToDate).collect(Collectors.toList()));
        if (!CollectionUtils.isEmpty(allParticipantsMonthly)) {
            List<List<ParticipantEntity>> participantEntities = allParticipantsMonthly.entrySet().stream().map(this::participantMapper).collect(Collectors.toList());
            participantEntities.stream().flatMap(List::stream).forEach(s -> participantRepository.save(s));
        }
        return allParticipantsMonthly;
    }

    private <R> List<ParticipantEntity> participantMapper(Map.Entry<Date, List<Participant>> list) {
        Date date = list.getKey();
        List<Participant> participants = list.getValue();
        List<ParticipantEntity> participantEntities = new ArrayList<>();
        participants.stream().skip(1).forEach(participant -> {
            ParticipantEntity participantEntity = new ParticipantEntity();
            participantEntity.setCreatedDate(date);
            participantEntity.setClientType(participant.getClientType());
            participantEntity.setFutureIndexLong(participant.getFutureIndexLong());
            participantEntity.setFutureIndexShort(participant.getFutureIndexShort());
            participantEntity.setFutureStockLong(participant.getFutureStockLong());
            participantEntity.setFutureStockShort(participant.getFutureStockShort());
            participantEntity.setOptionIndexCallLong(participant.getOptionIndexCallLong());
            participantEntity.setOptionIndexCallShort(participant.getOptionIndexCallShort());
            participantEntity.setOptionIndexPutLong(participant.getOptionIndexPutLong());
            participantEntity.setOptionIndexPutShort(participant.getOptionIndexPutShort());
            participantEntity.setOptionStockCallLong(participant.getOptionStockCallLong());
            participantEntity.setOptionStockCallShort(participant.getOptionStockCallShort());
            participantEntity.setOptionStockPutLong(participant.getOptionStockPutLong());
            participantEntity.setOptionStockPutShort(participant.getOptionStockPutShort());
            participantEntity.setTotalLongContracts(participant.getTotalLongContracts());
            participantEntity.setTotalShortContracts(participant.getTotalShortContracts());
            participantEntities.add(participantEntity);
        });
        return participantEntities;
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

    private static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    private <R> Date localDateToDate(LocalDate localDate) {
        return asDate(localDate);
    }

    @SneakyThrows
    private Map<Date, List<Participant>> getParticipants(final List<Date> dates) {
        Map<Date, List<Participant>> fiidiimap = new HashMap<>();
        dates.stream().forEach(s -> {
            try {
                final String day = DateUtil.getDate(s);
                InputStream data = participantClient.caller2("NSE-PARTICIPANTS", day, "csv");
                fiidiimap.put(s, convertToJson(data));
            } catch (Exception e) {
                //e.printStackTrace();
            }
        });
        return fiidiimap;
    }

    @SneakyThrows
    private List<Participant> convertToJson(final InputStream data) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(data))) {
            List<Participant> fiis = in
                    .lines()
                    .skip(1)
                    .map(this::mapper)
                    .collect(Collectors.toList());
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return fiis;
        }
    }

    private <R> Participant mapper(String line) {
        Pattern pattern = Pattern.compile(",");
        String[] x = pattern.split(line);
        Participant participant = new Participant();
        participant.setClientType(x[0]);
        participant.setFutureIndexLong(x[1]);
        participant.setFutureIndexShort(x[2]);
        participant.setFutureStockLong(x[3]);
        participant.setFutureStockShort(x[4]);
        participant.setOptionIndexCallLong(x[5]);
        participant.setOptionIndexCallShort(x[6]);
        participant.setOptionIndexPutLong(x[7]);
        participant.setOptionIndexPutShort(x[8]);
        participant.setOptionStockCallLong(x[9]);
        participant.setOptionStockCallShort(x[10]);
        participant.setOptionStockPutLong(x[11]);
        participant.setOptionStockPutShort(x[12]);
        participant.setTotalLongContracts(x[13]);
        participant.setTotalShortContracts(x[14]);
        return participant;
    }

    public Map<Date, List<Participant>> loadFiiDiiParticipantForXdays(String days) {
        Map<Date, List<Participant>> participantsMap = getParticipants(getXdays(days).stream().map(this::localDateToDate).collect(Collectors.toList()));
        if (!CollectionUtils.isEmpty(participantsMap)) {
            List<List<ParticipantEntity>> participantsEntities = participantsMap.entrySet().stream().map(this::participantMapper).collect(Collectors.toList());
            participantsEntities.stream().flatMap(List::stream).forEach(s -> participantRepository.save(s));
        }
        return participantsMap;
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

    public List<Map<Date, Participant>> getParticipantByDate(final String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<ParticipantEntity> dateSpecificFiiDiiParticipants = participantRepository.getByCreatedDate(asDate(localDate));
        if (!CollectionUtils.isEmpty(dateSpecificFiiDiiParticipants)) {
            return dateSpecificFiiDiiParticipants.stream().filter(s -> checkMatchingParticipants(s)).map(this::convertJsonToEnity).collect(Collectors.toList());
        }
        return null;
    }

    private boolean checkMatchingParticipants(final ParticipantEntity participant) {
        Predicate<ParticipantEntity> fiiParticipant = fii -> fii.getClientType().equalsIgnoreCase("FII");
        Predicate<ParticipantEntity> diiParticipant = fii -> fii.getClientType().equalsIgnoreCase("DII");
        Predicate<ParticipantEntity> proParticipant = fii -> fii.getClientType().equalsIgnoreCase("Pro");
        Predicate<ParticipantEntity> clientParticipant = fii -> fii.getClientType().equalsIgnoreCase("Client");
        Predicate<ParticipantEntity> totalParticipant = fii -> fii.getClientType().equalsIgnoreCase("TOTAL");
        Predicate<ParticipantEntity> result = fiiParticipant.or(diiParticipant).or(proParticipant).or(clientParticipant).or(totalParticipant);
        return result.test(participant);
    }

    public List<Map<Date, Participant>> getParticipantsByFii(final int days) {
        List<ParticipantEntity> fiiDiiParticipantEntities = participantRepository.findAll();
        List<ParticipantEntity> sorted = fiiDiiParticipantEntities.stream().filter(s -> checkMatchingParticipants(s)).sorted(Comparator.comparing(ParticipantEntity::getCreatedDate).reversed()).limit(days * 5).collect(Collectors.toList());
        return sorted.stream().sorted(Comparator.comparing(ParticipantEntity::getCreatedDate)).map(this::convertJsonToEnity).collect(Collectors.toList());
    }

    private <R> Map<Date, Participant> convertJsonToEnity(final ParticipantEntity participantEntity) {
        final Map<Date, Participant> dataFiiDiiParticipantMap = new HashMap<>();
        final Participant participant = new Participant();
        participant.setClientType(participantEntity.getClientType());
        participant.setFutureIndexLong(participantEntity.getFutureIndexLong());
        participant.setFutureIndexShort(participantEntity.getFutureIndexShort());
        participant.setFutureStockLong(participantEntity.getFutureStockLong());
        participant.setFutureStockShort(participantEntity.getFutureStockShort());
        participant.setOptionIndexCallLong(participantEntity.getOptionIndexCallLong());
        participant.setOptionIndexCallShort(participantEntity.getOptionIndexCallShort());
        participant.setOptionIndexPutLong(participantEntity.getOptionIndexPutLong());
        participant.setOptionIndexPutShort(participantEntity.getOptionIndexPutShort());
        participant.setOptionStockCallLong(participantEntity.getOptionStockCallLong());
        participant.setOptionStockCallShort(participantEntity.getOptionStockCallShort());
        participant.setOptionStockPutLong(participantEntity.getOptionStockPutLong());
        participant.setOptionStockPutShort(participantEntity.getOptionStockPutShort());
        participant.setTotalLongContracts(participantEntity.getTotalLongContracts());
        participant.setTotalShortContracts(participantEntity.getTotalShortContracts());
        dataFiiDiiParticipantMap.put(participantEntity.getCreatedDate(), participant);
        return dataFiiDiiParticipantMap;
    }
}
