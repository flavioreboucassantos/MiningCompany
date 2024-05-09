package com.br.flavioreboucassantos.miningcompany.gateway_bff.service;

import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto.DtoProposalDetails;
import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.service.ServiceBase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public interface ServiceProposal extends ServiceBase {

	DtoProposalDetails getDtoProposalDetailsById(@PathParam("id") final long idProposal);

	Response createProposal(final DtoProposalDetails dtoProposalDetails);

	Response removeProposal(final long idProposal);

}
