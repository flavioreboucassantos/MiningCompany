package com.br.flavioreboucassantos.miningcompany.proposta.service;

import com.br.flavioreboucassantos.miningcompany.proposta.entity.EntityProposal;
import com.br.flavioreboucassantos.miningcompany.proposta.message.EventsKafka;
import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto.DtoProposalDetails;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public final class ImplServiceProposal implements ServiceProposal {

	private final EventsKafka eventsKafka;

	@Inject
	public ImplServiceProposal(final EventsKafka eventsKafka) {
		this.eventsKafka = eventsKafka;
	}

	@Override
	public DtoProposalDetails findDtoProposalDetails(final long idProposal) {
		EntityProposal entityProposal = EntityProposal.findById(idProposal);
		return (entityProposal == null) ? null : entityProposal.extractDtoDetails();
	}

	@Override
	public DtoProposalDetails tryFindDtoProposalDetails(final long idProposal) {
		return findDtoProposalDetails(idProposal);
	}

	@Transactional
	public boolean tryCreateProposal(final EntityProposal entityProposal) {
		try {
			entityProposal.persist();
		} catch (PersistenceException e) {
			return false;
		}
		return true;
	}

	@Override
	public void createProposal(final DtoProposalDetails dtoProposalDetails) {
		final EntityProposal entityProposal = new EntityProposal(dtoProposalDetails);
		if (tryCreateProposal(entityProposal))
			eventsKafka.sendNewKafkaEvent(entityProposal.extractDto());
	}

	@Override
	@Transactional
	public void removeProposal(final long idProposal) {
		EntityProposal.deleteById(idProposal);
	}

}
