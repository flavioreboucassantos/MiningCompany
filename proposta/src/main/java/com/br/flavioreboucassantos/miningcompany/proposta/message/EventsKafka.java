package com.br.flavioreboucassantos.miningcompany.proposta.message;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto.DtoProposal;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public final class EventsKafka {

	private final Logger LOG = LoggerFactory.getLogger(EventsKafka.class);

	@Channel("proposal")
	Emitter<DtoProposal> emitter;

	public void sendNewKafkaEvent(DtoProposal dtoProposal) {
		LOG.info("-- Enviando Nova Proposta para Tópico Kafka --");
		emitter
				.send(
						dtoProposal
				)
				.toCompletableFuture()
				.join();
	}

}
