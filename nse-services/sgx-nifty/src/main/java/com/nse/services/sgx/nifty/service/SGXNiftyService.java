package com.nse.services.sgx.nifty.service;

import com.connector.sgx.nifty.SGXNiftyOptionConnectorClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nse.services.sgx.nifty.json.SGXNiftyOpenInterestJson;
import com.nse.services.sgx.nifty.json.SGXNiftyOpenInterestJsonWrapper;
import com.nse.services.sgx.nifty.model.SGXNiftyOpenInterest;
import com.nse.services.sgx.nifty.repository.SGXNiftyOpenInterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SGXNiftyService {

    @Autowired
    private SGXNiftyOpenInterestRepository repository;

    @Autowired
    private SGXNiftyOptionConnectorClient client;

    public List<SGXNiftyOpenInterest> futureOpenInterest(final String marketType) throws IOException {
        List<SGXNiftyOpenInterest> openInterests = new ArrayList<>();
        Optional<String> response = client.connect(marketType);
        if (response.isPresent()) {
            SGXNiftyOpenInterestJsonWrapper wrapper = new ObjectMapper().readValue(response.get(), SGXNiftyOpenInterestJsonWrapper.class);
            List<SGXNiftyOpenInterestJson> jsons = wrapper.getData();
            if (!CollectionUtils.isEmpty(jsons)) {
                openInterests = jsons.stream().map(s -> mapper(s, marketType)).collect(Collectors.toList());
                openInterests.stream().forEach(s -> repository.save(s));
            }
        }
        return openInterests;
    }

    private <R> SGXNiftyOpenInterest mapper(final SGXNiftyOpenInterestJson item, final String type) {
        final SGXNiftyOpenInterest openInterest = new SGXNiftyOpenInterest();
        openInterest.setOpenInterest(item.getOpenInterest());
        openInterest.setTotalVolume(item.getTotalVolume());
        openInterest.setDate(item.getDate());
        openInterest.setType(type);
        return openInterest;
    }
}
