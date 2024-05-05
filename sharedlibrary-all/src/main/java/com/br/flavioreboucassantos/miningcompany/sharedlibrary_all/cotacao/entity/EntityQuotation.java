package com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.cotacao.entity;

import java.math.BigDecimal;
import java.util.Objects;

import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto.DtoQuotation;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "quotation", uniqueConstraints = { @UniqueConstraint(columnNames = { "pair", "timestamp" }) })
public class EntityQuotation extends PanacheEntityBase {

	@Id
	@Column(name = "id_quotation")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idQuotation;

	@Column(name = "timestamp")
	private long timestamp;

	@Column(name = "currency_price")
	private BigDecimal currencyPrice;

	@Column(name = "pct_change")
	private BigDecimal pctChange;

	@Column(name = "pair")
	private String pair;

	public EntityQuotation() {
	}

	public EntityQuotation(
			final long timestamp,
			final BigDecimal bid,
			final BigDecimal pctChange,
			final String pairQuotation) {
		this.timestamp = timestamp;
		currencyPrice = bid;
		this.pctChange = pctChange;
		pair = pairQuotation;
	}

	public EntityQuotation(final DtoQuotation dtoQuotation) {
		timestamp = dtoQuotation.timestamp();
		currencyPrice = dtoQuotation.currencyPrice();
		pctChange = dtoQuotation.pctChange();
		pair = dtoQuotation.pair();
	}

	public long getIdQuotation() {
		return idQuotation;
	}

	public void setIdQuotation(long idQuotation) {
		this.idQuotation = idQuotation;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public BigDecimal getCurrencyPrice() {
		return currencyPrice;
	}

	public void setCurrencyPrice(BigDecimal currencyPrice) {
		this.currencyPrice = currencyPrice;
	}

	public BigDecimal getPctChange() {
		return pctChange;
	}

	public void setPctChange(BigDecimal pctChange) {
		this.pctChange = pctChange;
	}

	public String getPair() {
		return pair;
	}

	public void setPair(String pair) {
		this.pair = pair;
	}

	@Override
	public int hashCode() {
		return Objects.hash(currencyPrice, idQuotation, pair, pctChange, timestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityQuotation other = (EntityQuotation) obj;
		return Objects.equals(currencyPrice, other.currencyPrice) && idQuotation == other.idQuotation
				&& Objects.equals(pair, other.pair) && Objects.equals(pctChange, other.pctChange)
				&& timestamp == other.timestamp;
	}

}
