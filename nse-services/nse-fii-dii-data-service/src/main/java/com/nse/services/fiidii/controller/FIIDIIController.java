package com.nse.services.fiidii.controller;

import com.nse.services.fiidii.model.FiiDiiParticipant;
import com.nse.services.fiidii.model.FiiDiiJsonResponse;
import com.nse.services.fiidii.model.ParticipantJson;
import com.nse.services.fiidii.service.FIIDIIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/nse/participant")
public class FIIDIIController {

    @Autowired
    private FIIDIIService fiidiiService;

    @GetMapping
    public String test() {
        return "ok";
    }

    @GetMapping("/daily")
    public Map<Date, List<FiiDiiParticipant>> getFiiDiiDataDaily() {
        return fiidiiService.getFiiDiiParticipantsDaily();
    }

    @GetMapping("/weekly")
    public Map<Date, List<FiiDiiParticipant>> getFiiDiiDataWeekly() {
        return fiidiiService.getFiiDiiParticipantsWeekly();
    }

    @GetMapping("/monthly")
    public Map<Date, List<FiiDiiParticipant>> getFiiDiiDataMonthly() {
        return fiidiiService.getFiiDiiParticipantsMonthly();
    }

    @GetMapping("/{days}")
    public Map<Date, List<FiiDiiParticipant>> getFiiDiiParticipantForXdays(@PathVariable final String days) {
        return fiidiiService.getFiiDiiParticipantForXdays(days);
    }

    @GetMapping("/fii/{days}")
    public List<FiiDiiJsonResponse> getParticipantsByFii(@PathVariable final String days) {
        List<Map<Date, FiiDiiParticipant>> result = fiidiiService.getParticipantsByFii(Integer.parseInt(days));
        List<FiiDiiJsonResponse> responses = new ArrayList<>();
        if (!CollectionUtils.isEmpty(result)) {
            result.stream().forEach(s -> responses.add(generateResponse(s)));
        }
        return responses;
    }

    @GetMapping("/fii/bydate/{date}")
    public List<FiiDiiJsonResponse> getParticipantByDate(@PathVariable final String date) {
        //e.g "2022-04-01" - 1st april 2022
        List<FiiDiiJsonResponse> responses = new ArrayList<>();
        fiidiiService.getParticipantByDate(date).stream().forEach(s -> responses.add(generateResponse(s)));
        return responses;
    }

    private FiiDiiJsonResponse generateResponse(Map<Date, FiiDiiParticipant> request) {
        ParticipantJson resultPercentage = new ParticipantJson();
        FiiDiiJsonResponse response = new FiiDiiJsonResponse();
        response.setDate(request.entrySet().stream().findFirst().get().getKey());

        FiiDiiParticipant participant = request.entrySet().stream().findFirst().get().getValue();
        resultPercentage.setClientType(participant.getClientType());

        //future index long
        resultPercentage.setFutureIndexLong(participant.getFutureIndexLong());
        resultPercentage.setFutureIndexLongPer(findFuturePercentageIncrease(participant.getFutureIndexLong(), participant.getFutureIndexShort()));

        //future index short
        resultPercentage.setFutureIndexShort(participant.getFutureIndexShort());
        resultPercentage.setFutureIndexShortPer(findFuturePercentageIncrease(participant.getFutureIndexShort(), participant.getFutureIndexLong()));

        //future stock long
        resultPercentage.setFutureStockLong(participant.getFutureStockLong());
        resultPercentage.setFutureStockLongPer(findFuturePercentageIncrease(participant.getFutureStockLong(), participant.getFutureStockShort()));

        //future stock short
        resultPercentage.setFutureStockShort(participant.getFutureIndexShort());
        resultPercentage.setFutureStockShortPer(findFuturePercentageIncrease(participant.getFutureStockShort(), participant.getFutureStockLong()));

        //option index call long
        resultPercentage.setOptionIndexCallLong(participant.getOptionIndexCallLong());
        resultPercentage.setOptionIndexCallLongPer(findOptionPercentageIncrease(participant.getOptionIndexCallLong(),
                participant.getOptionIndexPutLong(), participant.getOptionIndexCallShort(), participant.getOptionIndexPutShort()));

        //option index put long
        resultPercentage.setOptionIndexPutLong(participant.getOptionIndexPutLong());
        resultPercentage.setOptionIndexPutLongPer(findOptionPercentageIncrease(participant.getOptionIndexPutLong(),
                participant.getOptionIndexCallLong(), participant.getOptionIndexCallShort(), participant.getOptionIndexPutShort()));

        //option index call short
        resultPercentage.setOptionIndexCallShort(participant.getOptionIndexCallShort());
        resultPercentage.setOptionIndexCallShortPer(findOptionPercentageIncrease(participant.getOptionIndexCallShort(),
                participant.getOptionIndexCallLong(), participant.getOptionIndexPutLong(), participant.getOptionIndexPutShort()));

        //option index put short
        resultPercentage.setOptionIndexPutShort(participant.getOptionIndexPutShort());
        resultPercentage.setOptionIndexPutShortPer(findOptionPercentageIncrease(participant.getOptionIndexPutShort(),
                participant.getOptionIndexCallLong(), participant.getOptionIndexPutLong(), participant.getOptionIndexCallShort()));

        //option stock call long
        resultPercentage.setOptionStockCallLong(participant.getOptionStockCallLong());
        resultPercentage.setOptionStockCallLongPer(findOptionPercentageIncrease(participant.getOptionStockCallLong(),
                participant.getOptionStockPutLong(), participant.getOptionStockCallShort(), participant.getOptionStockPutShort()));

        //option stock put long
        resultPercentage.setOptionStockPutLong(participant.getOptionStockPutLong());
        resultPercentage.setOptionStockPutLongPer(findOptionPercentageIncrease(participant.getOptionStockPutLong(), participant.getOptionStockCallLong()
                , participant.getOptionStockCallShort(), participant.getOptionStockPutShort()));

        //option stock call short
        resultPercentage.setOptionStockCallShort(participant.getOptionStockCallShort());
        resultPercentage.setOptionStockCallShortPer(findOptionPercentageIncrease(participant.getOptionStockCallShort(), participant.getOptionStockPutLong(),
                participant.getOptionStockCallLong(), participant.getOptionStockPutShort()));

        //option stock put short
        resultPercentage.setOptionStockPutShort(participant.getOptionStockPutShort());
        resultPercentage.setOptionStockPutShortPer(findOptionPercentageIncrease(participant.getOptionStockPutShort(), participant.getOptionStockCallShort(),
                participant.getOptionStockPutLong(), participant.getOptionStockCallLong()));

        resultPercentage.setTotalLongContracts(participant.getTotalLongContracts());
        resultPercentage.setTotalShortContracts(participant.getTotalShortContracts());
        resultPercentage.setTotalLongShortRatio(getLongShortRation(participant.getTotalLongContracts(), participant.getTotalShortContracts()));

        response.setResult(resultPercentage);
        return response;
    }

    private double getLongShortRation(String totalLongContracts, String totalShortContracts) {
        Double totalLong = Double.parseDouble(totalLongContracts);
        Double totalShort = Double.parseDouble(totalShortContracts);
        Double result = (totalLong / totalShort);
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(result));
    }

    private Double findOptionPercentageIncrease(String optionIndexCallLong, String optionIndexPutLong, String optionIndexCallShort, String optionIndexPutShort) {
        Double callLong = Double.parseDouble(optionIndexCallLong);
        Double putLong = Double.parseDouble(optionIndexPutLong);
        Double callShort = Double.parseDouble(optionIndexCallShort);
        Double putShort = Double.parseDouble(optionIndexPutShort);
        Double percent = (callLong / (callLong + putLong + callShort + putShort)) * 100;
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(percent));
    }

    private Double findFuturePercentageIncrease(String first, String second) {
        Double f = Double.parseDouble(first);
        Double s = Double.parseDouble(second);
        Double percent = (f / (f + s)) * 100;
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(percent));
    }
}
