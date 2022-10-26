package com.nse.services.open.interest.processor;

import com.nse.services.open.interest.json.OpenInterestChainResponse;
import com.nse.services.open.interest.model.OpenInterestEntity;

public interface OpenInterestRangeProcessor {

    public OpenInterestEntity processWithRange(final OpenInterestChainResponse response, final String symbol, final String range) throws Exception;

}
