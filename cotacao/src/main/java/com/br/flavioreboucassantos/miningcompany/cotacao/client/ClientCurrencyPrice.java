package com.br.flavioreboucassantos.miningcompany.cotacao.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.br.flavioreboucassantos.miningcompany.cotacao.dto.DtoCurrencyPrice;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@ApplicationScoped
@RegisterRestClient
@Path("/last")
public interface ClientCurrencyPrice {

	@GET
	@Path("/{pair}")
	DtoCurrencyPrice getByPair(@PathParam("pair") String pair);

}