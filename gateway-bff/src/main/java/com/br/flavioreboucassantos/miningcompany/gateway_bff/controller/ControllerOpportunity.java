package com.br.flavioreboucassantos.miningcompany.gateway_bff.controller;

import java.util.Date;

import org.eclipse.microprofile.jwt.JsonWebToken;

import com.br.flavioreboucassantos.miningcompany.gateway_bff.service.ServiceReport;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/gateway/opportunity")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Authenticated
public final class ControllerOpportunity {

	private final ServiceReport serviceReport;
	private final JsonWebToken jsonWebToken;

	@Inject
	public ControllerOpportunity(final ServiceReport serviceReport, final JsonWebToken jsonWebToken) {
		this.serviceReport = serviceReport;
		this.jsonWebToken = jsonWebToken;
	}

	@GET
	@Path("/list")
	@RolesAllowed({ "user", "manager" })
	public Response listAll() {
		return Response.ok().entity(serviceReport.findAll()).build();
	}

	@GET
	@Path("/report")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@RolesAllowed({ "user", "manager" })
	public Response generateReport() {
		return Response
				.ok(serviceReport.generateCSVOpportunityReport(), MediaType.APPLICATION_OCTET_STREAM)
				.header("content-disposition", "attachment; filename = " + new Date() + "--oportunidades-venda.csv")
				.build();
	}

}
