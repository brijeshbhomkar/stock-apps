package nse.services.open.interest.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenInterestChainFiltered implements Serializable {

    @JsonProperty(value = "CE")
    private CE ce;

    @JsonProperty(value = "PE")
    private CE pe;

    @JsonProperty(value = "data")
    private List<OpenInterestChain> data;

    public CE getCe() {
        return ce;
    }

    public void setCe(CE ce) {
        this.ce = ce;
    }

    public List<OpenInterestChain> getData() {
        return data;
    }

    public void setData(List<OpenInterestChain> data) {
        this.data = data;
    }

    public CE getPe() {
        return pe;
    }

    public void setPe(CE pe) {
        this.pe = pe;
    }
}
