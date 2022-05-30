package com.nse.services.fiidii.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "FIIDII_PARTICIPANT")
public class FiiDiiParticipantEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date createdDate;

    private String clientType;
    private String futureIndexLong;
    private String futureIndexShort;
    private String futureStockLong;
    private String futureStockShort;
    private String optionIndexCallLong;
    private String optionIndexPutLong;
    private String optionIndexCallShort;
    private String optionIndexPutShort;
    private String optionStockCallLong;
    private String optionStockPutLong;
    private String optionStockCallShort;
    private String optionStockPutShort;
    private String totalLongContracts;
    private String totalShortContracts;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public String getClientType() {
        return clientType;
    }

    public String getFutureIndexLong() {
        return futureIndexLong;
    }

    public String getFutureIndexShort() {
        return futureIndexShort;
    }

    public String getFutureStockLong() {
        return futureStockLong;
    }

    public String getFutureStockShort() {
        return futureStockShort;
    }

    public String getOptionIndexCallLong() {
        return optionIndexCallLong;
    }

    public String getOptionIndexCallShort() {
        return optionIndexCallShort;
    }

    public String getOptionIndexPutLong() {
        return optionIndexPutLong;
    }

    public String getOptionIndexPutShort() {
        return optionIndexPutShort;
    }

    public String getOptionStockCallLong() {
        return optionStockCallLong;
    }

    public String getOptionStockCallShort() {
        return optionStockCallShort;
    }

    public String getOptionStockPutLong() {
        return optionStockPutLong;
    }

    public String getOptionStockPutShort() {
        return optionStockPutShort;
    }

    public String getTotalLongContracts() {
        return totalLongContracts;
    }

    public String getTotalShortContracts() {
        return totalShortContracts;
    }

    public void setFutureIndexShort(String futureIndexShort) {
        this.futureIndexShort = futureIndexShort;
    }

    public void setFutureStockLong(String futureStockLong) {
        this.futureStockLong = futureStockLong;
    }

    public void setFutureStockShort(String futureStockShort) {
        this.futureStockShort = futureStockShort;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOptionIndexCallLong(String optionIndexCallLong) {
        this.optionIndexCallLong = optionIndexCallLong;
    }

    public void setOptionIndexCallShort(String optionIndexCallShort) {
        this.optionIndexCallShort = optionIndexCallShort;
    }

    public void setOptionIndexPutLong(String optionIndexPutLong) {
        this.optionIndexPutLong = optionIndexPutLong;
    }

    public void setOptionIndexPutShort(String optionIndexPutShort) {
        this.optionIndexPutShort = optionIndexPutShort;
    }

    public void setOptionStockCallLong(String optionStockCallLong) {
        this.optionStockCallLong = optionStockCallLong;
    }

    public void setOptionStockCallShort(String optionStockCallShort) {
        this.optionStockCallShort = optionStockCallShort;
    }

    public void setOptionStockPutLong(String optionStockPutLong) {
        this.optionStockPutLong = optionStockPutLong;
    }

    public void setOptionStockPutShort(String optionStockPutShort) {
        this.optionStockPutShort = optionStockPutShort;
    }

    public void setTotalLongContracts(String totalLongContracts) {
        this.totalLongContracts = totalLongContracts;
    }

    public void setTotalShortContracts(String totalShortContracts) {
        this.totalShortContracts = totalShortContracts;
    }

    public void setFutureIndexLong(String futureIndexLong) {
        this.futureIndexLong = futureIndexLong;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FiiDiiParticipantEntity that = (FiiDiiParticipantEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(clientType, that.clientType) &&
                Objects.equals(futureIndexLong, that.futureIndexLong) &&
                Objects.equals(futureIndexShort, that.futureIndexShort) &&
                Objects.equals(futureStockLong, that.futureStockLong) &&
                Objects.equals(futureStockShort, that.futureStockShort) &&
                Objects.equals(optionIndexCallLong, that.optionIndexCallLong) &&
                Objects.equals(optionIndexPutLong, that.optionIndexPutLong) &&
                Objects.equals(optionIndexCallShort, that.optionIndexCallShort) &&
                Objects.equals(optionIndexPutShort, that.optionIndexPutShort) &&
                Objects.equals(optionStockCallLong, that.optionStockCallLong) &&
                Objects.equals(optionStockPutLong, that.optionStockPutLong) &&
                Objects.equals(optionStockCallShort, that.optionStockCallShort) &&
                Objects.equals(optionStockPutShort, that.optionStockPutShort) &&
                Objects.equals(totalLongContracts, that.totalLongContracts) &&
                Objects.equals(totalShortContracts, that.totalShortContracts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientType, futureIndexLong, futureIndexShort, futureStockLong, futureStockShort, optionIndexCallLong, optionIndexPutLong, optionIndexCallShort, optionIndexPutShort, optionStockCallLong, optionStockPutLong, optionStockCallShort, optionStockPutShort, totalLongContracts, totalShortContracts);
    }

    @Override
    public String toString() {
        return "FiiDiiParticipantEntity{" +
                "id=" + id +
                ", clientType='" + clientType + '\'' +
                ", futureIndexLong='" + futureIndexLong + '\'' +
                ", futureIndexShort='" + futureIndexShort + '\'' +
                ", futureStockLong='" + futureStockLong + '\'' +
                ", futureStockShort='" + futureStockShort + '\'' +
                ", optionIndexCallLong='" + optionIndexCallLong + '\'' +
                ", optionIndexPutLong='" + optionIndexPutLong + '\'' +
                ", optionIndexCallShort='" + optionIndexCallShort + '\'' +
                ", optionIndexPutShort='" + optionIndexPutShort + '\'' +
                ", optionStockCallLong='" + optionStockCallLong + '\'' +
                ", optionStockPutLong='" + optionStockPutLong + '\'' +
                ", optionStockCallShort='" + optionStockCallShort + '\'' +
                ", optionStockPutShort='" + optionStockPutShort + '\'' +
                ", totalLongContracts='" + totalLongContracts + '\'' +
                ", totalShortContracts='" + totalShortContracts + '\'' +
                '}';
    }
}
