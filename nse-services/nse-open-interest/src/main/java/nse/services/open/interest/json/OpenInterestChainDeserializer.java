package nse.services.open.interest.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OpenInterestChainDeserializer extends JsonDeserializer<OpenInterestChainResponse> {

    @Override
    public OpenInterestChainResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        final OpenInterestChainResponse response = new OpenInterestChainResponse();
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        JsonNode records = node.get("records");
        JsonNode filtered = node.get("filtered");
        populateRecords(records, response);
        populateFiltered(filtered, response);
        return response;
    }

    private void populateFiltered(JsonNode node, OpenInterestChainResponse response) {
        final OpenInterestChainFiltered records = new OpenInterestChainFiltered();
        ArrayNode arrayNode = (ArrayNode) node.get("data");
        if (arrayNode != null && arrayNode.size() > 0) {
            List<OpenInterestChain> wrappers = new ArrayList<>();
            arrayNode.forEach(s -> {
                final OpenInterestChain wrapper = new OpenInterestChain();
                wrapper.setStrikePrice(s.get("strikePrice").asDouble());
                wrapper.setExpiryDate(s.get("expiryDate").asText());
                OpenInterestChainJson peOpenInterestChainJson = populatePE(s.get("PE"), "PE");
                wrapper.setPe(peOpenInterestChainJson);
                OpenInterestChainJson ceOpenInterestChainJson = populatePE(s.get("CE"), "CE");
                wrapper.setCe(ceOpenInterestChainJson);
                wrappers.add(wrapper);
            });
            records.setData(wrappers);

            final CE ce = new CE();
            JsonNode ceNode = node.get("CE");
            ce.setTotOI(ceNode.get("totOI").asText());
            ce.setTotVol(ceNode.get("totVol").asText());
            records.setCe(ce);

            final CE pe = new CE();
            JsonNode peNode = node.get("PE");
            pe.setTotOI(peNode.get("totOI").asText());
            pe.setTotVol(peNode.get("totVol").asText());
            records.setPe(pe);

            response.setFiltered(records);
        }
    }

    private void populateRecords(JsonNode node, OpenInterestChainResponse response) {
        final OpenInterestChainRecords records = new OpenInterestChainRecords();
        if (node != null) {
            ArrayNode expDatesNode = (ArrayNode) node.get("expiryDates");
            if (expDatesNode.size() > 0) {
                List<String> expiries = new ArrayList<>();
                expDatesNode.forEach(s -> expiries.add(s.asText()));
                records.setExpiryDates(expiries);
            }
            ArrayNode arrayNode = (ArrayNode) node.get("data");
            if (arrayNode.size() > 0) {
                List<OpenInterestChain> wrappers = new ArrayList<>();
                arrayNode.forEach(s -> {
                    final OpenInterestChain wrapper = new OpenInterestChain();
                    wrapper.setStrikePrice(s.get("strikePrice").asDouble());
                    wrapper.setExpiryDate(s.get("expiryDate").asText());
                    OpenInterestChainJson peOpenInterestChainJson = populatePE(s.get("PE"), "PE");
                    wrapper.setPe(peOpenInterestChainJson);
                    OpenInterestChainJson ceOpenInterestChainJson = populatePE(s.get("CE"), "CE");
                    wrapper.setCe(ceOpenInterestChainJson);
                    wrappers.add(wrapper);
                });
                records.setData(wrappers);

                records.setTimestamp(node.get("timestamp").asText()); //TODO:

                records.setUnderlyingValue(node.get("underlyingValue").asText());
                ArrayNode strikePrices = (ArrayNode) node.get("strikePrices");
                if (strikePrices.size() > 0) {
                    List<Integer> strkPrices = new ArrayList<>();
                    for (int i = 0; i < strikePrices.size(); i++) {
                        strkPrices.add(strikePrices.get(i).asInt());
                    }
                    records.setStrikePrices(strkPrices);
                }
            }
            response.setRecords(records);
        }
    }

    private OpenInterestChainJson populatePE(JsonNode s, String type) {
        if (s != null) {
            final OpenInterestChainJson pe = new OpenInterestChainJson();
            //create open interest
            pe.setType(type);
            pe.setStrikePrice(s.get("strikePrice").asDouble());
            pe.setExpiryDate(s.get("expiryDate").asText());
            pe.setUnderlying(s.get("underlying").asText());
            pe.setIdentifier(s.get("identifier").asText());
            pe.setOpenInterest(s.get("openInterest").asDouble());
            pe.setChangeinOpenInterest(s.get("changeinOpenInterest").asDouble());
            pe.setPchangeinOpenInterest(s.get("pchangeinOpenInterest").asDouble());
            pe.setTotalTradedVolume(s.get("totalTradedVolume").asDouble());
            pe.setImpliedVolatility(s.get("impliedVolatility").asDouble());
            pe.setLastPrice(s.get("lastPrice").asDouble());
            pe.setChange(s.get("change").asDouble());
            pe.setTotalBuyQuantity(s.get("totalBuyQuantity").asDouble());
            pe.setTotalSellQuantity(s.get("totalSellQuantity").asDouble());
            pe.setBidQty(s.get("bidQty").asDouble());
            pe.setAskQty(s.get("askQty").asDouble());
            pe.setAskPrice(s.get("askPrice").asDouble());
            pe.setUnderlyingValue(s.get("underlyingValue").asDouble());
            return pe;
        }
        return null;
    }
}
