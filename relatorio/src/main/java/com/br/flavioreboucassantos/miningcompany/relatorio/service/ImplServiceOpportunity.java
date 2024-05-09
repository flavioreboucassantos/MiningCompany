package com.br.flavioreboucassantos.miningcompany.relatorio.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import com.br.flavioreboucassantos.miningcompany.relatorio.entity.EntityOpportunity;
import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.cotacao.entity.EntityQuotation;
import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto.DtoOpportunity;
import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto.DtoProposal;
import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto.DtoQuotation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public final class ImplServiceOpportunity implements ServiceOpportunity {

	@Transactional
	public void tryCreateOpportunity(final DtoProposal dtoProposal, final BigDecimal lastCurrencyQuotation) {
		final EntityOpportunity entityOpportunity = new EntityOpportunity(dtoProposal, lastCurrencyQuotation);
		entityOpportunity.persist();
	}

	@Override
	@ActivateRequestContext
	public void createOpportunity(final DtoProposal dtoProposal) {
		final List<EntityQuotation> listEntityQuotation = EntityQuotation.list("order by idQuotation desc limit 1");

		final BigDecimal lastCurrencyQuotation = listEntityQuotation.get(0).getCurrencyPrice();

		tryCreateOpportunity(dtoProposal, lastCurrencyQuotation);
	}

	@Override
	@Transactional
	public void createQuotation(final DtoQuotation dtoQuotation) {
		final EntityQuotation entityQuotation = new EntityQuotation(dtoQuotation);
		try {
			entityQuotation.persist();
		} catch (ConstraintViolationException e) {
			/*
			 * @Table(name = "quotation", uniqueConstraints = { @UniqueConstraint(columnNames = { "timestamp" }) })
			 */
		}
	}

	@Override
	public List<DtoOpportunity> findAll() {
		final List<DtoOpportunity> listDtoOpportunity = new ArrayList<DtoOpportunity>();

		final List<EntityOpportunity> listEntityOpportunity = EntityOpportunity.findAll().list();
		for (EntityOpportunity entityOpportunity : listEntityOpportunity)
			listDtoOpportunity
					.add(new DtoOpportunity(
							entityOpportunity.getIdProposal(),
							entityOpportunity.getCustomer(),
							entityOpportunity.getPriceTonne(),
							entityOpportunity.getLastCurrencyQuotation()));

		return listDtoOpportunity;
	}
}
