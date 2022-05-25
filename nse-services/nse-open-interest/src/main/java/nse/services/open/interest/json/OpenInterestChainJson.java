package nse.services.open.interest.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenInterestChainJson implements Serializable  {

    @JsonProperty(value = "strikePrice")
    private Double strikePrice;

    @JsonProperty(value = "expiryDate")
    private String expiryDate;

    @JsonProperty(value = "underlying")
    private String underlying;

    @JsonProperty(value = "identifier")
    private String identifier;

    @JsonProperty(value = "openInterest")
    private Double openInterest;

    @JsonProperty(value = "changeinOpenInterest")
    private Double changeinOpenInterest;

    @JsonProperty(value = "pchangeinOpenInterest")
    private Double pchangeinOpenInterest;

    @JsonProperty(value = "totalTradedVolume")
    private Double totalTradedVolume;

    @JsonProperty(value = "impliedVolatility")
    private Double impliedVolatility;

    @JsonProperty(value = "lastPrice")
    private Double lastPrice;

    @JsonProperty(value = "change")
    private Double change;

    @JsonProperty(value = "pChange")
    private Double pChange;

    @JsonProperty(value = "totalBuyQuantity")
    private Double totalBuyQuantity;

    @JsonProperty(value = "totalSellQuantity")
    private Double totalSellQuantity;

    @JsonProperty(value = "bidQty")
    private Double bidQty;

    @JsonProperty(value = "bidprice")
    private Double bidprice;

    @JsonProperty(value = "askQty")
    private Double askQty;

    @JsonProperty(value = "askPrice")
    private Double askPrice;

    @JsonProperty(value = "underlyingValue")
    private Double underlyingValue;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getStrikePrice() {
        return strikePrice;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public Double getChangeinOpenInterest() {
        return changeinOpenInterest;
    }

    public Double getChange() {
        return change;
    }

    public Double getImpliedVolatility() {
        return impliedVolatility;
    }

    public Double getOpenInterest() {
        return openInterest;
    }

    public Double getLastPrice() {
        return lastPrice;
    }

    public Double getpChange() {
        return pChange;
    }

    public Double getPchangeinOpenInterest() {
        return pchangeinOpenInterest;
    }

    public Double getTotalTradedVolume() {
        return totalTradedVolume;
    }

    public Double getBidQty() {
        return bidQty;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getUnderlying() {
        return underlying;
    }

    public Double getAskPrice() {
        return askPrice;
    }

    public Double getTotalBuyQuantity() {
        return totalBuyQuantity;
    }

    public Double getAskQty() {
        return askQty;
    }

    public Double getBidprice() {
        return bidprice;
    }

    public Double getTotalSellQuantity() {
        return totalSellQuantity;
    }

    public Double getUnderlyingValue() {
        return underlyingValue;
    }

    public void setStrikePrice(Double strikePrice) {
        this.strikePrice = strikePrice;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public void setChangeinOpenInterest(Double changeinOpenInterest) {
        this.changeinOpenInterest = changeinOpenInterest;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setOpenInterest(Double openInterest) {
        this.openInterest = openInterest;
    }

    public void setPchangeinOpenInterest(Double pchangeinOpenInterest) {
        this.pchangeinOpenInterest = pchangeinOpenInterest;
    }

    public void setImpliedVolatility(Double impliedVolatility) {
        this.impliedVolatility = impliedVolatility;
    }

    public void setTotalTradedVolume(Double totalTradedVolume) {
        this.totalTradedVolume = totalTradedVolume;
    }

    public void setUnderlying(String underlying) {
        this.underlying = underlying;
    }

    public void setLastPrice(Double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public void setAskPrice(Double askPrice) {
        this.askPrice = askPrice;
    }

    public void setAskQty(Double askQty) {
        this.askQty = askQty;
    }

    public void setBidprice(Double bidprice) {
        this.bidprice = bidprice;
    }

    public void setBidQty(Double bidQty) {
        this.bidQty = bidQty;
    }

    public void setpChange(Double pChange) {
        this.pChange = pChange;
    }

    public void setTotalBuyQuantity(Double totalBuyQuantity) {
        this.totalBuyQuantity = totalBuyQuantity;
    }

    public void setTotalSellQuantity(Double totalSellQuantity) {
        this.totalSellQuantity = totalSellQuantity;
    }

    public void setUnderlyingValue(Double underlyingValue) {
        this.underlyingValue = underlyingValue;
    }
}
