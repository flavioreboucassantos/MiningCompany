package com.br.flavioreboucassantos.miningcompany.relatorio.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto.DtoProposal;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "opportunity")
public class EntityOpportunity extends PanacheEntityBase {

	@Id
	@Column(name = "id_opportunity")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idOpportunity;

	@Column(name = "date")
	private Date date;

	@Column(name = "id_proposal")
	private long idProposal;

	@Column(name = "customer")
	private String customer;

	@Column(name = "price_tonne")
	private BigDecimal priceTonne;

	@Column(name = "last_currency_quotation")
	private BigDecimal lastCurrencyQuotation;

	public EntityOpportunity() {
	}

	public EntityOpportunity(final DtoProposal dtoProposal, final BigDecimal lastCurrencyQuotation) {
		date = new Date();
		idProposal = dtoProposal.idProposal();
		customer = dtoProposal.customer();
		priceTonne = dtoProposal.priceTonne();
		this.lastCurrencyQuotation = lastCurrencyQuotation;
	}

	public long getIdOpportunity() {
		return idOpportunity;
	}

	public void setIdOpportunity(long idOpportunity) {
		this.idOpportunity = idOpportunity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getIdProposal() {
		return idProposal;
	}

	public void setIdProposal(long idProposal) {
		this.idProposal = idProposal;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public BigDecimal getPriceTonne() {
		return priceTonne;
	}

	public void setPriceTonne(BigDecimal priceTonne) {
		this.priceTonne = priceTonne;
	}

	public BigDecimal getLastCurrencyQuotation() {
		return lastCurrencyQuotation;
	}

	public void setLastCurrencyQuotation(BigDecimal lastCurrencyQuotation) {
		this.lastCurrencyQuotation = lastCurrencyQuotation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer, date, idOpportunity, idProposal, lastCurrencyQuotation, priceTonne);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityOpportunity other = (EntityOpportunity) obj;
		return Objects.equals(customer, other.customer) && Objects.equals(date, other.date)
				&& idOpportunity == other.idOpportunity && idProposal == other.idProposal
				&& Objects.equals(lastCurrencyQuotation, other.lastCurrencyQuotation)
				&& Objects.equals(priceTonne, other.priceTonne);
	}

}
