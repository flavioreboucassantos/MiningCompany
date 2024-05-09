package com.br.flavioreboucassantos.miningcompany.gateway_bff.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto.DtoProposalDetails;

import io.quarkus.oidc.token.propagation.AccessToken;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@RegisterRestClient
@AccessToken
@Path("/api/proposal")
public interface RestClientProposal {

	@GET
	@Path("/{id}")
	DtoProposalDetails getDtoProposalDetailsById(final @PathParam("id") long idProposal);

	@POST
	Response createProposal(final DtoProposalDetails dtoProposalDetails);

	@DELETE
	@Path("/{id}")
	Response removeProposal(final @PathParam("id") long idProposal);

}
