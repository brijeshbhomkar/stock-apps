package com.data.service.dataservice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "GAP_UP_STOCK")
public class GapUpStock implements Serializable {

	private static final long serialVersionUID = -3004694907490262438L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "sym_id")
	private String symbolId;

	@Column(name = "sym")
	private String symbol;

	@Column(name = "open")
	private Double open;

	@Column(name = "high")
	private Double high;

	@Column(name = "low")
	private Double low;

	@Column(name = "ltp")
	private Double ltP;

	@Column(name = "trd_vol")
	private Double trdVol;

	@Column(name = "trd_vol_m")
	private Double trdVolM;

	@Column(name = "prev_close")
	private Double previousClose;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public Double getLtP() {
		return ltP;
	}

	public void setLtP(Double ltP) {
		this.ltP = ltP;
	}

	public Double getTrdVol() {
		return trdVol;
	}

	public void setTrdVol(Double trdVol) {
		this.trdVol = trdVol;
	}

	public Double getTrdVolM() {
		return trdVolM;
	}

	public void setTrdVolM(Double trdVolM) {
		this.trdVolM = trdVolM;
	}

	public Double getPreviousClose() {
		return previousClose;
	}

	public void setPreviousClose(Double previousClose) {
		this.previousClose = previousClose;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSymbolId() {
		return symbolId;
	}

	public void setSymbolId(String symbolId) {
		this.symbolId = symbolId;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

}
