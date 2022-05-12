package com.stocks.oi.document;

import java.io.Serializable;

public class DailyParticipantVolumeDocument implements Serializable {

    private String clientType;
    private long futureIndexLong;
    private long futureIndexShort;
    private long futureStockLong;
    private long futureStockShort;
    private long optionIndexCallLong;
    private long optionIndexPutLong;
    private long optionIndexCallShort;
    private long optionIndexPutShort;
    private long optionStockCallLong;
    private long optionStockPutLong;
    private long optionStockCallShort;
    private long optionStockPutShort;
    private long totalLongContracts;
    private long totalShortContracts;

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public long getFutureIndexLong() {
        return futureIndexLong;
    }

    public void setFutureIndexLong(long futureIndexLong) {
        this.futureIndexLong = futureIndexLong;
    }

    public long getFutureIndexShort() {
        return futureIndexShort;
    }

    public void setFutureIndexShort(long futureIndexShort) {
        this.futureIndexShort = futureIndexShort;
    }

    public long getFutureStockLong() {
        return futureStockLong;
    }

    public void setFutureStockLong(long futureStockLong) {
        this.futureStockLong = futureStockLong;
    }

    public long getFutureStockShort() {
        return futureStockShort;
    }

    public void setFutureStockShort(long futureStockShort) {
        this.futureStockShort = futureStockShort;
    }

    public long getOptionIndexCallLong() {
        return optionIndexCallLong;
    }

    public void setOptionIndexCallLong(long optionIndexCallLong) {
        this.optionIndexCallLong = optionIndexCallLong;
    }

    public long getOptionIndexPutLong() {
        return optionIndexPutLong;
    }

    public void setOptionIndexPutLong(long optionIndexPutLong) {
        this.optionIndexPutLong = optionIndexPutLong;
    }

    public long getOptionIndexCallShort() {
        return optionIndexCallShort;
    }

    public void setOptionIndexCallShort(long optionIndexCallShort) {
        this.optionIndexCallShort = optionIndexCallShort;
    }

    public long getOptionIndexPutShort() {
        return optionIndexPutShort;
    }

    public void setOptionIndexPutShort(long optionIndexPutShort) {
        this.optionIndexPutShort = optionIndexPutShort;
    }

    public long getOptionStockCallLong() {
        return optionStockCallLong;
    }

    public void setOptionStockCallLong(long optionStockCallLong) {
        this.optionStockCallLong = optionStockCallLong;
    }

    public long getOptionStockPutLong() {
        return optionStockPutLong;
    }

    public void setOptionStockPutLong(long optionStockPutLong) {
        this.optionStockPutLong = optionStockPutLong;
    }

    public long getOptionStockCallShort() {
        return optionStockCallShort;
    }

    public void setOptionStockCallShort(long optionStockCallShort) {
        this.optionStockCallShort = optionStockCallShort;
    }

    public long getOptionStockPutShort() {
        return optionStockPutShort;
    }

    public void setOptionStockPutShort(long optionStockPutShort) {
        this.optionStockPutShort = optionStockPutShort;
    }

    public long getTotalLongContracts() {
        return totalLongContracts;
    }

    public void setTotalLongContracts(long totalLongContracts) {
        this.totalLongContracts = totalLongContracts;
    }

    public long getTotalShortContracts() {
        return totalShortContracts;
    }

    public void setTotalShortContracts(long totalShortContracts) {
        this.totalShortContracts = totalShortContracts;
    }
}
