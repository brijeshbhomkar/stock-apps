package com.nse.services.participant.controller;

import com.nse.services.participant.api.ParticipantsApi;
import com.nse.services.participant.model.Participant;
import com.nse.services.participant.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/v2/api/")
public class ParticipantController implements ParticipantsApi {

    @Autowired
    private ParticipantService participantService;

    @Override
    public ResponseEntity<Map<String, List<Participant>>> getDailyParticipants() {
        Map<Date, List<Participant>> participantsMap = participantService.getDailyParticipants();
        Map response = null;
        if (!CollectionUtils.isEmpty(participantsMap)) {
            response = participantsMap.entrySet().stream().map(this::mapper)
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> Arrays.asList(e.getValue())));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }


    @Override
    public ResponseEntity<Map<String, List<Participant>>> getWeeklyParticipants() {
        Map<Date, List<Participant>> participantsMap = participantService.getWeeklyParticipants();
        Map response = null;
        if (!CollectionUtils.isEmpty(participantsMap)) {
            response = participantsMap.entrySet().stream().map(this::mapper)
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> Arrays.asList(e.getValue())));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Map<String, List<Participant>>> getMonthlyParticipants() {
        Map<Date, List<Participant>> participantsMap = participantService.getMonthlyParticipants();
        Map response = null;
        if (!CollectionUtils.isEmpty(participantsMap)) {
            response = participantsMap.entrySet().stream().map(this::mapper)
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> Arrays.asList(e.getValue())));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Map<String, List<Participant>>> getParticipantByDate(String date) {
        Map<Date, List<Participant>> participantsMap = participantService.getMonthlyParticipants();
        Map response = null;
        if (!CollectionUtils.isEmpty(participantsMap)) {
            response = participantsMap.entrySet().stream().map(this::mapper)
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> Arrays.asList(e.getValue())));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    public ParticipantService getParticipantService() {
        return participantService;
    }

    @Override
    public ResponseEntity<Map<String, List<Participant>>> getDiiParticipantByDate(String date) {

        Map<Date, List<Participant>> participantsMap = participantService.getDiiParticipantByDate(date);
        Map response = null;
        if (!CollectionUtils.isEmpty(participantsMap)) {
            response = participantsMap.entrySet().stream().map(this::mapper)
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> Arrays.asList(e.getValue())));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Map<String, List<Participant>>> getDiiParticipantByDays(String days) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, List<Participant>>> getFiiParticipantByDate(String date) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, List<Participant>>> getFiiParticipantByDays(String days) {
        return null;
    }



    private <R> Map.Entry<String, List<Participant>> mapper(Map.Entry<Date, List<Participant>> entry) {
        Date date = entry.getKey();
        List<Participant> participants = entry.getValue();
        return Map.entry(date.toString(), participants);
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }
}
