package com.br.flavioreboucassantos.miningcompany.proposta.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto.DtoProposal;
import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto.DtoProposalDetails;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "proposal")
public class EntityProposal extends PanacheEntityBase {

	@Id
	@Column(name = "id_proposal")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idProposal;

	@Column(name = "customer")
	private String customer;

	@Column(name = "price_tonne")
	private BigDecimal priceTonne;

	@Column(name = "tonnes")
	private int tonnes;

	@Column(name = "country")
	private String country;

	@Column(name = "proposal_validity_days")
	private int proposalValidityDays;

	@Column(name = "created")
	private Date created;

	public EntityProposal() {
	}

	public EntityProposal(final DtoProposalDetails dtoProposalDetails) {
		customer = dtoProposalDetails.customer();
		priceTonne = dtoProposalDetails.priceTonne();
		tonnes = dtoProposalDetails.tonnes();
		country = dtoProposalDetails.country();
		proposalValidityDays = dtoProposalDetails.proposalValidityDays();
		created = new Date();
	}

	public final Optional<EntityProposal> findByCustomer(final String customer) {
		return Optional.of(find("customer", customer).firstResult());
	}

	public final DtoProposalDetails extractDtoDetails() {
		return new DtoProposalDetails(
				idProposal,
				customer,
				priceTonne,
				tonnes,
				country,
				proposalValidityDays);
	}

	public final DtoProposal extractDto() {
		return new DtoProposal(
				idProposal,
				customer,
				priceTonne);
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

	public int getTonnes() {
		return tonnes;
	}

	public void setTonnes(int tonnes) {
		this.tonnes = tonnes;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getProposalValidityDays() {
		return proposalValidityDays;
	}

	public void setProposalValidityDays(int proposalValidityDays) {
		this.proposalValidityDays = proposalValidityDays;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public int hashCode() {
		return Objects.hash(country, created, customer, idProposal, priceTonne, proposalValidityDays, tonnes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityProposal other = (EntityProposal) obj;
		return Objects.equals(country, other.country) && Objects.equals(created, other.created)
				&& Objects.equals(customer, other.customer) && idProposal == other.idProposal
				&& Objects.equals(priceTonne, other.priceTonne) && proposalValidityDays == other.proposalValidityDays
				&& tonnes == other.tonnes;
	}

}
