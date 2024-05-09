package com.br.flavioreboucassantos.miningcompany.gateway_bff.service;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.br.flavioreboucassantos.miningcompany.gateway_bff.client.RestClientProposal;
import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto.DtoProposalDetails;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public final class ImplServiceProposal implements ServiceProposal {

	private final RestClientProposal restClientProposal;

	@Inject
	public ImplServiceProposal(@RestClient final RestClientProposal restClientProposal) {
		this.restClientProposal = restClientProposal;
	}

	@Override
	public DtoProposalDetails getDtoProposalDetailsById(final long idProposal) {
		return restClientProposal.getDtoProposalDetailsById(idProposal);
	}

	@Override
	public Response createProposal(final DtoProposalDetails dtoProposalDetails) {
		return restClientProposal.createProposal(dtoProposalDetails);
	}

	@Override
	public Response removeProposal(final long idProposal) {
		return restClientProposal.removeProposal(idProposal);
	}

}
