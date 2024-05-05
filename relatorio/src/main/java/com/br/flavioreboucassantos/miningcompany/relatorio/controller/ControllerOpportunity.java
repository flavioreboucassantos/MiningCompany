package com.br.flavioreboucassantos.miningcompany.relatorio.controller;

import java.util.Date;

import com.br.flavioreboucassantos.miningcompany.relatorio.service.ServiceOpportunity;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/opportunity")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public final class ControllerOpportunity {

	private final ServiceOpportunity serviceOpportunity;

	@Inject
	public ControllerOpportunity(final ServiceOpportunity serviceOpportunity) {
		this.serviceOpportunity = serviceOpportunity;
	}

	@GET
	@Path("/report")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response generateReport() {
		return Response
				.ok(serviceOpportunity.generateCSVOpportunityReport(), MediaType.APPLICATION_OCTET_STREAM)
				.header("content-disposition", "attachment; filename = " + new Date() + "--oportunidades-venda.csv")
				.build();
	}

}
