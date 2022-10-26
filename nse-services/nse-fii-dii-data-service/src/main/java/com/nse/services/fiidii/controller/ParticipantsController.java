package com.nse.services.fiidii.controller;

import com.nse.services.fiidii.model.FiiDiiParticipant;
import com.nse.services.fiidii.model.FiiDiiJsonResponse;
import com.nse.services.fiidii.model.ParticipantJson;
import com.nse.services.fiidii.service.FIIDIIService;
import com.nse.services.fiidii.util.ResponseGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/nse/participant")
public class FIIDIIController {

    @Autowired
    private FIIDIIService fiidiiService;

    @GetMapping("/{day}")
    public Map<Date, List<FiiDiiParticipant>> loadFiiDiiDataForDay(@PathVariable final String day) {
        return fiidiiService.loadFiiDiiDataForDay(day);
    }

    @GetMapping("/daily")
    public Map<Date, List<FiiDiiParticipant>> loadFiiDiiDataDaily() {
        return fiidiiService.loadFiiDiiParticipantsDaily();
    }

    @GetMapping("/weekly")
    public Map<Date, List<FiiDiiParticipant>> loadFiiDiiDataWeekly() {
        return fiidiiService.loadFiiDiiParticipantsWeekly();
    }

    @GetMapping("/monthly")
    public Map<Date, List<FiiDiiParticipant>> loadFiiDiiDataMonthly() {
        return fiidiiService.loadFiiDiiParticipantsMonthly();
    }

    @GetMapping("/range/{days}")
    public Map<Date, List<FiiDiiParticipant>> loadFiiDiiParticipantForXdays(@PathVariable final String days) {
        return fiidiiService.loadFiiDiiParticipantForXdays(days);
    }

    @GetMapping("/fii/{days}")
    public List<FiiDiiJsonResponse> getParticipantsByFii(@PathVariable final String days) {
        List<Map<Date, FiiDiiParticipant>> result = fiidiiService.getParticipantsByFii(Integer.parseInt(days));
        List<FiiDiiJsonResponse> responses = new ArrayList<>();
        if (!CollectionUtils.isEmpty(result)) {
            result.stream().forEach(s -> responses.add(ResponseGenerator.getInstance().generateResponse(s)));
        }
        return responses.stream().filter(s -> s.getResult().getClientType().equalsIgnoreCase("FII")).collect(Collectors.toList());
    }

    @GetMapping("/dii/{days}")
    public List<FiiDiiJsonResponse> getParticipantsByDii(@PathVariable final String days) {
        List<Map<Date, FiiDiiParticipant>> result = fiidiiService.getParticipantsByFii(Integer.parseInt(days));
        List<FiiDiiJsonResponse> responses = new ArrayList<>();
        if (!CollectionUtils.isEmpty(result)) {
            result.stream().forEach(s -> responses.add(ResponseGenerator.getInstance().generateResponse(s)));
        }
        return responses.stream().filter(s -> s.getResult().getClientType().equalsIgnoreCase("DII")).collect(Collectors.toList());
    }

    @GetMapping("/fii/bydate/{date}")
    public List<FiiDiiJsonResponse> getParticipantFiiByDate(@PathVariable final String date) {
        //e.g "2022-04-01" - 1st april 2022
        List<FiiDiiJsonResponse> responses = new ArrayList<>();
        fiidiiService.getParticipantByDate(date).stream().forEach(s -> responses.add(ResponseGenerator.getInstance().generateResponse(s)));
        return responses.stream().filter(s -> s.getResult().getClientType().equalsIgnoreCase("FII")).collect(Collectors.toList());
    }

    @GetMapping("/dii/bydate/{date}")
    public List<FiiDiiJsonResponse> getParticipantDiiByDate(@PathVariable final String date) {
        //e.g "2022-04-01" - 1st april 2022
        List<FiiDiiJsonResponse> responses = new ArrayList<>();
        fiidiiService.getParticipantByDate(date).stream().forEach(s -> responses.add(ResponseGenerator.getInstance().generateResponse(s)));
        return responses.stream().filter(s -> s.getResult().getClientType().equalsIgnoreCase("DII")).collect(Collectors.toList());
    }
}
