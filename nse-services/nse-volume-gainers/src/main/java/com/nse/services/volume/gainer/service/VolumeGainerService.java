package com.nse.services.volume.gainer.service;

import com.common.exception.ApplicationException;
import com.connector.groww.GrowwConnector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nse.services.volume.gainer.json.common.Items;
import com.nse.services.volume.gainer.json.common.JsonData;
import com.nse.services.volume.gainer.json.gainers.VolumeGainerJsonResponseWrapper;
import com.nse.services.volume.gainer.model.VolumeGainer;
import com.nse.services.volume.gainer.repository.VolumeGainerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class VolumeGainerService {

    @Autowired
    private VolumeGainerRepository volumeGainerRepository;

    @Autowired
    private GrowwConnector growwConnector;

    public List<VolumeGainer> getVolumeGainersNifty100() throws ApplicationException {
        List<VolumeGainer> volumeGainers = new ArrayList<>();
        JsonData data = makeApiCall("GIDXNIFTY100", "TRADED_BY_VOLUME", 20);
        if (data != null) {
            List<VolumeGainer> topGainerList = convertJsonToEntity(data);
            topGainerList.stream().forEach(s -> volumeGainers.add(volumeGainerRepository.save(s)));
        }
        return volumeGainers;
    }

    private List<VolumeGainer> convertJsonToEntity(final JsonData data) {
        return data.getItems().stream().map(s -> volumeGainerMapper(data.getTimestamp(), s)).collect(Collectors.toList());
    }

    private <R> VolumeGainer volumeGainerMapper(final long timestamp, final Items item) {
        final VolumeGainer volumeGainer = new VolumeGainer();
        volumeGainer.setDate(new Date(timestamp));
        volumeGainer.setCompanyName(item.getCompany().getCompanyName());
        volumeGainer.setEquityType(item.getCompany().getEquityType());
        volumeGainer.setNseSymbol(item.getCompany().getNseScriptCode());
        volumeGainer.setLtp(item.getStats().getLtp());
        volumeGainer.setYearHighPrice(item.getStats().getYearHighPrice());
        volumeGainer.setYearLowPrice(item.getStats().getYearLowPrice());
        Double high = Double.valueOf(item.getStats().getYearHighPrice());
        Double low = Double.valueOf(item.getStats().getYearLowPrice());
        Double perc = (high) / (high + low);
        DecimalFormat df = new DecimalFormat("#.##");
        volumeGainer.setPercChange(Double.valueOf(df.format(perc)));
        return volumeGainer;
    }

    private JsonData makeApiCall(String marketType, String filterType, int size) throws ApplicationException {
        Optional<String> response = growwConnector.connect(marketType, filterType, size);
        JsonData jsonData = new JsonData();
        try {
            VolumeGainerJsonResponseWrapper wrapper = new ObjectMapper().readValue(response.get(), VolumeGainerJsonResponseWrapper.class);
            if (wrapper != null && wrapper.getVolumeGainerJsonResponse() != null) {
                jsonData.setTimestamp(wrapper.getTimeStamp());
                jsonData.setItems(wrapper.getVolumeGainerJsonResponse().getVolumeGainerJson().getItems());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    public List<VolumeGainer> getVolumeGainersNifty500() throws ApplicationException {
        List<VolumeGainer> topGainers = new ArrayList<>();
        JsonData data = makeApiCall("GIDXNIFTY500", "TRADED_BY_VOLUME", 20);
        if (data != null) {
            List<VolumeGainer> topGainerList = convertJsonToEntity(data);
            topGainerList.stream().forEach(s -> topGainers.add(volumeGainerRepository.save(s)));
        }
        return topGainers;
    }

    public List<VolumeGainer> getVolumeGainersSMCP100() throws ApplicationException {
        List<VolumeGainer> topGainers = new ArrayList<>();
        JsonData data = makeApiCall("GIDXNIFSMCP100", "TRADED_BY_VOLUME", 20);
        if (data != null) {
            List<VolumeGainer> topGainerList = convertJsonToEntity(data);
            topGainerList.stream().forEach(s -> topGainers.add(volumeGainerRepository.save(s)));
        }
        return topGainers;
    }
}
