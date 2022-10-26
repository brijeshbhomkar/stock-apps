package com.nse.services.open.interest.processor;

import com.nse.services.open.interest.json.OpenInterestChainResponse;
import com.nse.services.open.interest.model.OpenInterestEntity;

public interface OpenInterestProcessor {

    public OpenInterestEntity processWithoutRange(final OpenInterestChainResponse response, final String symbol) throws Exception;

}
