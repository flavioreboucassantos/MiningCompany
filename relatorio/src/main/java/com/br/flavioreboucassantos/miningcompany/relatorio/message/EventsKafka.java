package com.br.flavioreboucassantos.miningcompany.relatorio.message;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.br.flavioreboucassantos.miningcompany.relatorio.service.ServiceOpportunity;
import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto.DtoProposal;
import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto.DtoQuotation;

import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public final class EventsKafka {

	private final Logger LOG = LoggerFactory.getLogger(EventsKafka.class);

	private final ServiceOpportunity serviceOpportunity;

	@Inject
	public EventsKafka(final ServiceOpportunity serviceOpportunity) {
		this.serviceOpportunity = serviceOpportunity;
	}

	@Incoming("quotation")
	@Blocking
	public void receiveQuotation(DtoQuotation dtoQuotation) {
		LOG.info("-- Recebendo Nova Cotação de Moeda do Tópico Kafka --");
		serviceOpportunity.createQuotation(dtoQuotation);
	}

	@Incoming("proposal")
	public void receiveProposal(DtoProposal dtoProposal) {
		LOG.info("-- Recebendo Nova Proposta do Tópico Kafka --");
		serviceOpportunity.createOpportunity(dtoProposal);
	}

}
