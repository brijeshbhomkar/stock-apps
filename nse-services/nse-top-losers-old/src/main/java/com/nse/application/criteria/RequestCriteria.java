package com.nse.application.criteria;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RequestCriteria implements Serializable {
    @NonNull
    private String marketType;
    @NonNull
    private String filterType;
    @NonNull
    private String size;
}
