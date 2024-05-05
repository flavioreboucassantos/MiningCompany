package com.br.flavioreboucassantos.miningcompany.cotacao.service;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.br.flavioreboucassantos.miningcompany.cotacao.client.ClientCurrencyPrice;
import com.br.flavioreboucassantos.miningcompany.cotacao.dto.DtoCurrencyPrice;
import com.br.flavioreboucassantos.miningcompany.cotacao.dto.DtoUsdBrl;
import com.br.flavioreboucassantos.miningcompany.cotacao.message.EventsKafka;
import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.cotacao.entity.EntityQuotation;
import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto.DtoQuotation;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public final class ServiceQuotation {

	private final Logger LOG = LoggerFactory.getLogger(ServiceQuotation.class);

	private final ClientCurrencyPrice clientCurrencyPrice;

	private final EventsKafka eventsKafka;

	private DtoUsdBrl tryGetCurrencyPrice(final String pairQuotation) {
		final DtoCurrencyPrice dtoCurrencyPrice = clientCurrencyPrice.getByPair(pairQuotation);
		return dtoCurrencyPrice.dtoUsdBrl();
	}

	@Inject
	public ServiceQuotation(@RestClient final ClientCurrencyPrice clientCurrencyPrice, final EventsKafka eventsKafka) {
		this.clientCurrencyPrice = clientCurrencyPrice;
		this.eventsKafka = eventsKafka;
	}

	@Transactional
	public boolean tryCreateQuotation(final DtoUsdBrl dtoUsdBrl, final String pairQuotation) {
		final EntityQuotation entityQuotation = new EntityQuotation(
				dtoUsdBrl.timestamp(),
				dtoUsdBrl.bid(),
				dtoUsdBrl.pctChange(),
				pairQuotation
		);

		try {
			entityQuotation.persist();
		} catch (PersistenceException e) {
			/*
			 * @Table(name = "quotation", uniqueConstraints = { @UniqueConstraint(columnNames = { "timestamp" }) })
			 */
			return false;
		}

		return true;
	}

	@Scheduled(every = "5s", identity = "task :: ServiceQuotation :: getCurrencyPrice()")
	public void scheduledCreateQuotation() {
		final String pairQuotation = "USD-BRL";

		final DtoUsdBrl dtoUsdBrl = tryGetCurrencyPrice(pairQuotation);

		LOG.info("-- Executando scheduler --");
		if (tryCreateQuotation(dtoUsdBrl, pairQuotation))
			eventsKafka
					.sendNewKafkaEvent(
							new DtoQuotation(
									dtoUsdBrl.timestamp(),
									dtoUsdBrl.bid(),
									dtoUsdBrl.pctChange(),
									pairQuotation
							)
					);
	}

}
