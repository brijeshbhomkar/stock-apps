package com.option.backtesting.pojo;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import java.io.Serializable;

public class OptionIDX implements Serializable {

    @CsvBindByPosition(position = 0)
    private String symbol;

    @CsvBindByPosition(position = 1)
    private String date;

    @CsvBindByPosition(position = 2)
    private String expiry;

    @CsvBindByPosition(position = 3)
    private String optionType;

    @CsvBindByPosition(position = 4)
    private String strikePrice;

    @CsvBindByPosition(position = 5)
    private String open;

    @CsvBindByPosition(position = 6)
    private String high;

    @CsvBindByPosition(position = 7)
    private String low;

    @CsvBindByPosition(position = 8)
    private String close;

    @CsvBindByPosition(position = 9)
    private String ltp;

    @CsvBindByPosition(position = 14)
    private String openInterest;

    @CsvBindByPosition(position = 15)
    private String changeInOi;

    @CsvBindByPosition(position = 11)
    private String noOfContracts;

    @CsvBindByPosition(position = 16)
    private String underlyingValue;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHigh() {
        return high;
    }

    public String getLtp() {
        return ltp;
    }

    public String getClose() {
        return close;
    }

    public String getLow() {
        return low;
    }

    public String getExpiry() {
        return expiry;
    }

    public String getChangeInOi() {
        return changeInOi;
    }

    public String getOpen() {
        return open;
    }

    public String getNoOfContracts() {
        return noOfContracts;
    }

    public String getOpenInterest() {
        return openInterest;
    }

    public String getOptionType() {
        return optionType;
    }

    public String getUnderlyingValue() {
        return underlyingValue;
    }

    public String getStrikePrice() {
        return strikePrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public void setLtp(String ltp) {
        this.ltp = ltp;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setStrikePrice(String strikePrice) {
        this.strikePrice = strikePrice;
    }

    public void setChangeInOi(String changeInOi) {
        this.changeInOi = changeInOi;
    }

    public void setNoOfContracts(String noOfContracts) {
        this.noOfContracts = noOfContracts;
    }

    public void setOpenInterest(String openInterest) {
        this.openInterest = openInterest;
    }

    public void setUnderlyingValue(String underlyingValue) {
        this.underlyingValue = underlyingValue;
    }

    @Override
    public String toString() {
        return "OptionIDX{" +
                "symbol='" + symbol + '\'' +
                ", date='" + date + '\'' +
                ", expiry='" + expiry + '\'' +
                ", optionType='" + optionType + '\'' +
                ", strikePrice='" + strikePrice + '\'' +
                ", open='" + open + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", close='" + close + '\'' +
                ", ltp='" + ltp + '\'' +
                ", openInterest='" + openInterest + '\'' +
                ", changeInOi='" + changeInOi + '\'' +
                ", noOfContracts='" + noOfContracts + '\'' +
                ", underlyingValue='" + underlyingValue + '\'' +
                '}';
    }
}
