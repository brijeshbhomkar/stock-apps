package com.nse.common.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@JsonIgnoreProperties
public class Company implements Serializable {

    private String isin;
    private String companyName;
    private String searchId;
    private String bseScriptCode;
    private String nseScriptCode;
    private String companyShortName;
    private String equityType;
    private String logoUrl;
    private String growwContractId;

}
