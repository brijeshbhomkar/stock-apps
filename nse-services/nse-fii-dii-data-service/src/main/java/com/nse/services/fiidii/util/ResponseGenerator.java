package com.nse.services.fiidii.util;

import com.nse.services.fiidii.model.FiiDiiJsonResponse;
import com.nse.services.fiidii.model.FiiDiiParticipant;
import com.nse.services.fiidii.model.ParticipantJson;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Map;

public final class ResponseGenerator {

    private static final ResponseGenerator responseGenerator = new ResponseGenerator();

    private ResponseGenerator() {
    }

    public FiiDiiJsonResponse generateResponse(final Map<Date, FiiDiiParticipant> request) {
        ParticipantJson resultPercentage = new ParticipantJson();
        FiiDiiJsonResponse response = new FiiDiiJsonResponse();
        response.setDate(request.keySet().stream().findAny().get());

        FiiDiiParticipant participant = request.entrySet().stream().findFirst().get().getValue();
        resultPercentage.setClientType(participant.getClientType());

        //future index long
        resultPercentage.setFutureIndexLong(participant.getFutureIndexLong());
        Double futureIndexLongPer = findFuturePercentageIncrease(participant.getFutureIndexLong(), participant.getFutureIndexShort());
        resultPercentage.setFutureIndexLongPer(futureIndexLongPer);
        //future index short
        resultPercentage.setFutureIndexShort(participant.getFutureIndexShort());
        Double futureIndexShortPer = findFuturePercentageIncrease(participant.getFutureIndexShort(), participant.getFutureIndexLong());
        resultPercentage.setFutureIndexShortPer(futureIndexShortPer);
        // future index short long ratio
        resultPercentage.setFutureIndexShortLongRatio(getShortLongRation(Double.toString(futureIndexShortPer), Double.toString(futureIndexLongPer)));

        //future stock long
        resultPercentage.setFutureStockLong(participant.getFutureStockLong());
        Double futureStockLongPer = findFuturePercentageIncrease(participant.getFutureStockLong(), participant.getFutureStockShort());
        resultPercentage.setFutureStockLongPer(futureStockLongPer);

        //future stock short
        resultPercentage.setFutureStockShort(participant.getFutureIndexShort());
        Double futureStockShortPer = findFuturePercentageIncrease(participant.getFutureStockShort(), participant.getFutureStockLong());
        resultPercentage.setFutureStockShortPer(futureStockShortPer);
        resultPercentage.setFutureStockShortLongRatio(getShortLongRation(Double.toString(futureStockShortPer), Double.toString(futureStockLongPer)));

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
        resultPercentage.setTotalShortLongRatio(getShortLongRation(participant.getTotalLongContracts(), participant.getTotalShortContracts()));

        //find out option index bullish and bearish ratio
        Double optionIndexBearish = Double.parseDouble(participant.getOptionIndexPutLong()) + Double.parseDouble(participant.getOptionIndexCallShort());
        Double optionIndexBullish = Double.parseDouble(participant.getOptionIndexCallLong()) + Double.parseDouble(participant.getOptionIndexPutShort());
        Double optionIndexBullishRatio = (optionIndexBullish / (optionIndexBullish + optionIndexBearish)) * 100;
        DecimalFormat df = new DecimalFormat("#.##");
        resultPercentage.setOptionIndexBullishRatio(Double.valueOf(df.format(optionIndexBullishRatio)));
        Double optionIndexBearishRatio = (optionIndexBearish / (optionIndexBearish + optionIndexBullish)) * 100;
        resultPercentage.setOptionIndexBearishRatio(Double.valueOf(df.format(optionIndexBearishRatio)));
        resultPercentage.setOptionIndexRatioDiff(Double.valueOf(df.format(optionIndexBearishRatio - optionIndexBullishRatio)));
        resultPercentage.setOptionIndexBearishByBullishRatio(getShortLongRation(Double.toString(optionIndexBearishRatio), Double.toString(optionIndexBullishRatio)));

        //find out option stock bullish and bearish ratio
        Double optionStockBearish = Double.parseDouble(participant.getOptionStockPutLong()) + Double.parseDouble(participant.getOptionStockCallShort());
        Double optionStockBullish = Double.parseDouble(participant.getOptionStockCallLong()) + Double.parseDouble(participant.getOptionStockPutShort());
        Double optionStockBullishRatio = (optionStockBullish / (optionStockBullish + optionStockBearish)) * 100;
        resultPercentage.setOptionStockBullishRatio(Double.valueOf(df.format(optionStockBullishRatio)));
        Double optionStockBearishRatio = (optionStockBearish / (optionStockBearish + optionStockBullish)) * 100;
        resultPercentage.setOptionStockBearishRatio(Double.valueOf(df.format(optionStockBearishRatio)));
        resultPercentage.setOptionStockRatioDiff(Double.valueOf(df.format(optionStockBearishRatio - optionStockBullishRatio)));

        resultPercentage.setOptionStockBearishByBullishRation(getShortLongRation(Double.toString(optionStockBearishRatio), Double.toString(optionStockBullishRatio)));

        response.setResult(resultPercentage);
        return response;
    }

    private double getShortLongRation(String shortContracts, String longContracts) {
        Double totalLong = Double.parseDouble(longContracts);
        Double totalShort = Double.parseDouble(shortContracts);
        Double result = (totalShort / totalLong);
        DecimalFormat df = new DecimalFormat("#.##");
        return (result.isNaN() || result.isInfinite()) ? 0.0 : Double.valueOf(df.format(result));
    }

    private Double findOptionPercentageIncrease(String optionIndexCallLong, String optionIndexPutLong, String optionIndexCallShort, String optionIndexPutShort) {
        Double callLong = Double.parseDouble(optionIndexCallLong);
        Double putLong = Double.parseDouble(optionIndexPutLong);
        Double callShort = Double.parseDouble(optionIndexCallShort);
        Double putShort = Double.parseDouble(optionIndexPutShort);
        Double percent = (callLong / (callLong + putLong + callShort + putShort)) * 100;
        DecimalFormat df = new DecimalFormat("#.##");
        return (percent.isNaN() || percent.isInfinite()) ? 0.0 : Double.valueOf(df.format(percent));
    }

    private Double findFuturePercentageIncrease(String first, String second) {
        Double f = Double.parseDouble(first);
        Double s = Double.parseDouble(second);
        Double percent = (f / (f + s)) * 100;
        DecimalFormat df = new DecimalFormat("#.##");
        return (percent.isNaN() || percent.isInfinite()) ? 0.0 : Double.valueOf(df.format(percent));
    }

    public static ResponseGenerator getInstance() {
        return responseGenerator;
    }
}
