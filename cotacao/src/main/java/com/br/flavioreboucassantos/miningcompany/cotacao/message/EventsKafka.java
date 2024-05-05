package com.br.flavioreboucassantos.miningcompany.cotacao.message;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto.DtoQuotation;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public final class EventsKafka {

	private final Logger LOG = LoggerFactory.getLogger(EventsKafka.class);

	@Channel("quotation")
	Emitter<DtoQuotation> emitter;

	public void sendNewKafkaEvent(DtoQuotation dtoQuotation) {
		LOG.info("-- Enviando Cotação para Tópico Kafka --");
		emitter
				.send(
						dtoQuotation
				)
				.toCompletableFuture()
				.join();
	}

}
