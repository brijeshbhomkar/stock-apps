package com.nse.services.open.interest.model;

import com.nse.services.open.interest.json.OpenInterestChainResponse;
import lombok.*;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpenInterestResponse {
    private OpenInterestChainResponse response;
    private String symbol;
    private String range;
}
