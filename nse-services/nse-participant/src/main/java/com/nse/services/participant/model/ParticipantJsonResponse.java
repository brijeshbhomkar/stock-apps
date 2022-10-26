package com.nse.services.participant.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@JsonIgnoreProperties
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantJsonResponse {
    private Date date;
    private ParticipantJson result;
}
