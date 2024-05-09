package com.br.flavioreboucassantos.miningcompany.gateway_bff.client;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto.DtoOpportunity;

import io.quarkus.oidc.token.propagation.AccessToken;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@RegisterRestClient
@AccessToken
@Path("/api/opportunity")
public interface RestClientReport {

	@GET
	@Path("/list")
	List<DtoOpportunity> listAll();

}
