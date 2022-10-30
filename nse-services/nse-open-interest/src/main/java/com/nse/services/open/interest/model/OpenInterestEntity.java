package com.nse.services.open.interest.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "OPEN_INTEREST")
public class OpenInterestEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime timestamp;
    private String symbol;
    private LocalDate expiryDate;
    private String identifier;
    private double callOpenInterest;
    private double callChangeOI;
    private double callPerChangeOI;
    private double callVolume;
    private double callAskPrice;
    private double callImpliedVolatility;
    private double callLtp;
    private double callLtpChange;
    private double callPercLtpChange;
    private double callStrikePrice;
    private double resistance;
    private double putOpenInterest;
    private double putChangeOI;
    private double putPerChangeOI;
    private double putVolume;
    private double putImpliedVolatility;
    private double putLtp;
    private double putLtpChange;
    private double putPercLtpChange;
    private double putStrikePrice;
    private double support;
    private double putAskPrice;
    private double pcrOI;
    private double pcrVolume;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpenInterestEntity that = (OpenInterestEntity) o;
        return id == that.id &&
                Double.compare(that.callOpenInterest, callOpenInterest) == 0 &&
                Double.compare(that.callChangeOI, callChangeOI) == 0 &&
                Double.compare(that.callPerChangeOI, callPerChangeOI) == 0 &&
                Double.compare(that.callVolume, callVolume) == 0 &&
                Double.compare(that.callAskPrice, callAskPrice) == 0 &&
                Double.compare(that.callImpliedVolatility, callImpliedVolatility) == 0 &&
                Double.compare(that.callLtp, callLtp) == 0 &&
                Double.compare(that.callLtpChange, callLtpChange) == 0 &&
                Double.compare(that.callPercLtpChange, callPercLtpChange) == 0 &&
                Double.compare(that.callStrikePrice, callStrikePrice) == 0 &&
                Double.compare(that.resistance, resistance) == 0 &&
                Double.compare(that.putOpenInterest, putOpenInterest) == 0 &&
                Double.compare(that.putChangeOI, putChangeOI) == 0 &&
                Double.compare(that.putPerChangeOI, putPerChangeOI) == 0 &&
                Double.compare(that.putVolume, putVolume) == 0 &&
                Double.compare(that.putImpliedVolatility, putImpliedVolatility) == 0 &&
                Double.compare(that.putLtp, putLtp) == 0 &&
                Double.compare(that.putLtpChange, putLtpChange) == 0 &&
                Double.compare(that.putPercLtpChange, putPercLtpChange) == 0 &&
                Double.compare(that.putStrikePrice, putStrikePrice) == 0 &&
                Double.compare(that.support, support) == 0 &&
                Double.compare(that.putAskPrice, putAskPrice) == 0 &&
                Double.compare(that.pcrOI, pcrOI) == 0 &&
                Double.compare(that.pcrVolume, pcrVolume) == 0 &&
                Objects.equals(symbol, that.symbol) &&
                Objects.equals(expiryDate, that.expiryDate) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, symbol, expiryDate, identifier, callOpenInterest, callChangeOI, callPerChangeOI, callVolume, callAskPrice, callImpliedVolatility, callLtp, callLtpChange, callPercLtpChange, callStrikePrice, resistance, putOpenInterest, putChangeOI, putPerChangeOI, putVolume, putImpliedVolatility, putLtp, putLtpChange, putPercLtpChange, putStrikePrice, support, putAskPrice, pcrOI, pcrVolume);
    }

    @Override
    public String toString() {
        return "OpenInterestEntity{" +
                "id=" + id +
                ", timestamp='" + timestamp + '\'' +
                ", symbol='" + symbol + '\'' +
                ", expiryDate=" + expiryDate +
                ", callOpenInterest=" + callOpenInterest +
                ", callChangeOI=" + callChangeOI +
                ", callPerChangeOI=" + callPerChangeOI +
                ", callVolume=" + callVolume +
                ", callAskPrice=" + callAskPrice +
                ", callImpliedVolatility=" + callImpliedVolatility +
                ", callLtp=" + callLtp +
                ", callLtpChange=" + callLtpChange +
                ", callPercLtpChange=" + callPercLtpChange +
                ", callStrikePrice=" + callStrikePrice +
                ", resistance=" + resistance +
                ", putOpenInterest=" + putOpenInterest +
                ", putChangeOI=" + putChangeOI +
                ", putPerChangeOI=" + putPerChangeOI +
                ", putVolume=" + putVolume +
                ", putImpliedVolatility=" + putImpliedVolatility +
                ", putLtp=" + putLtp +
                ", putLtpChange=" + putLtpChange +
                ", putPercLtpChange=" + putPercLtpChange +
                ", putStrikePrice=" + putStrikePrice +
                ", support=" + support +
                ", putAskPrice=" + putAskPrice +
                ", pcrOI=" + pcrOI +
                ", pcrVolume=" + pcrVolume +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getCallOpenInterest() {
        return callOpenInterest;
    }

    public void setCallOpenInterest(double callOpenInterest) {
        this.callOpenInterest = callOpenInterest;
    }

    public double getCallChangeOI() {
        return callChangeOI;
    }

    public void setCallChangeOI(double callChangeOI) {
        this.callChangeOI = callChangeOI;
    }

    public double getCallPerChangeOI() {
        return callPerChangeOI;
    }

    public void setCallPerChangeOI(double callPerChangeOI) {
        this.callPerChangeOI = callPerChangeOI;
    }

    public double getCallVolume() {
        return callVolume;
    }

    public void setCallVolume(double callVolume) {
        this.callVolume = callVolume;
    }

    public double getCallAskPrice() {
        return callAskPrice;
    }

    public void setCallAskPrice(double callAskPrice) {
        this.callAskPrice = callAskPrice;
    }

    public double getCallImpliedVolatility() {
        return callImpliedVolatility;
    }

    public void setCallImpliedVolatility(double callImpliedVolatility) {
        this.callImpliedVolatility = callImpliedVolatility;
    }

    public double getCallLtp() {
        return callLtp;
    }

    public void setCallLtp(double callLtp) {
        this.callLtp = callLtp;
    }

    public double getCallLtpChange() {
        return callLtpChange;
    }

    public void setCallLtpChange(double callLtpChange) {
        this.callLtpChange = callLtpChange;
    }

    public double getCallPercLtpChange() {
        return callPercLtpChange;
    }

    public void setCallPercLtpChange(double callPercLtpChange) {
        this.callPercLtpChange = callPercLtpChange;
    }

    public double getCallStrikePrice() {
        return callStrikePrice;
    }

    public void setCallStrikePrice(double callStrikePrice) {
        this.callStrikePrice = callStrikePrice;
    }

    public double getResistance() {
        return resistance;
    }

    public void setResistance(double resistance) {
        this.resistance = resistance;
    }

    public double getPutOpenInterest() {
        return putOpenInterest;
    }

    public void setPutOpenInterest(double putOpenInterest) {
        this.putOpenInterest = putOpenInterest;
    }

    public double getPutChangeOI() {
        return putChangeOI;
    }

    public void setPutChangeOI(double putChangeOI) {
        this.putChangeOI = putChangeOI;
    }

    public double getPutPerChangeOI() {
        return putPerChangeOI;
    }

    public void setPutPerChangeOI(double putPerChangeOI) {
        this.putPerChangeOI = putPerChangeOI;
    }

    public double getPutVolume() {
        return putVolume;
    }

    public void setPutVolume(double putVolume) {
        this.putVolume = putVolume;
    }

    public double getPutImpliedVolatility() {
        return putImpliedVolatility;
    }

    public void setPutImpliedVolatility(double putImpliedVolatility) {
        this.putImpliedVolatility = putImpliedVolatility;
    }

    public double getPutLtp() {
        return putLtp;
    }

    public void setPutLtp(double putLtp) {
        this.putLtp = putLtp;
    }

    public double getPutLtpChange() {
        return putLtpChange;
    }

    public void setPutLtpChange(double putLtpChange) {
        this.putLtpChange = putLtpChange;
    }

    public double getPutPercLtpChange() {
        return putPercLtpChange;
    }

    public void setPutPercLtpChange(double putPercLtpChange) {
        this.putPercLtpChange = putPercLtpChange;
    }

    public double getPutStrikePrice() {
        return putStrikePrice;
    }

    public void setPutStrikePrice(double putStrikePrice) {
        this.putStrikePrice = putStrikePrice;
    }

    public double getSupport() {
        return support;
    }

    public void setSupport(double support) {
        this.support = support;
    }

    public double getPutAskPrice() {
        return putAskPrice;
    }

    public void setPutAskPrice(double putAskPrice) {
        this.putAskPrice = putAskPrice;
    }

    public double getPcrOI() {
        return pcrOI;
    }

    public void setPcrOI(double pcrOI) {
        this.pcrOI = pcrOI;
    }

    public double getPcrVolume() {
        return pcrVolume;
    }

    public void setPcrVolume(double pcrVolume) {
        this.pcrVolume = pcrVolume;
    }
}

