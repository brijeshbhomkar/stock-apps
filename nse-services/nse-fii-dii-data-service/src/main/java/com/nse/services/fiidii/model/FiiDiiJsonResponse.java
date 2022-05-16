package com.nse.services.fiidii.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@JsonIgnoreProperties
@AllArgsConstructor
@NoArgsConstructor
public class FiiDiiJsonResponse {
    private Date date;
    private ParticipantJson result;
}
