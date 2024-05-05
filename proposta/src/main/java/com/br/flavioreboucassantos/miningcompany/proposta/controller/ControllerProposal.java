package com.br.flavioreboucassantos.miningcompany.proposta.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.br.flavioreboucassantos.miningcompany.proposta.dto.DtoProposalDetails;
import com.br.flavioreboucassantos.miningcompany.proposta.service.ServiceProposal;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/proposal")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public final class ControllerProposal {

	private final Logger LOG = LoggerFactory.getLogger(ControllerProposal.class);

	private final ServiceProposal serviceProposal;

	@Inject
	public ControllerProposal(final ServiceProposal serviceProposal) {
		this.serviceProposal = serviceProposal;
	}

	@GET
	@Path("/{id}")
	public Response findProposalDetails(final @PathParam("id") long idProposal) {
		DtoProposalDetails dtoProposalDetails;
		if ((dtoProposalDetails = serviceProposal.tryFindDtoProposalDetails(idProposal)) == null)
			return serviceProposal.disappointedFind().entity("findProposalDetails-id-NOT_FOUND").build();

		return Response.ok(dtoProposalDetails).build();
	}

	@POST
	public Response createProposal(DtoProposalDetails dtoProposalDetails) {

		LOG.info("--- Recebendo Proposta de Compra ---");

		serviceProposal.createNewProposal(dtoProposalDetails);

		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	public Response removeProposal(final @PathParam("id") long idProposal) {

		LOG.info("--- Removendo Proposta de Compra ---");

		serviceProposal.removeProposal(idProposal);

		return Response.ok().build();
	}

}
