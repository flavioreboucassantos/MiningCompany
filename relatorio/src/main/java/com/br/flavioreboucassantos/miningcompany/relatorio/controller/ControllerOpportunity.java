package com.br.flavioreboucassantos.miningcompany.relatorio.controller;

import org.eclipse.microprofile.jwt.JsonWebToken;

import com.br.flavioreboucassantos.miningcompany.relatorio.service.ServiceOpportunity;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
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
@Authenticated
public final class ControllerOpportunity {

	private final ServiceOpportunity serviceOpportunity;
	private final JsonWebToken jsonWebToken;

	@Inject
	public ControllerOpportunity(final ServiceOpportunity serviceOpportunity, final JsonWebToken jsonWebToken) {
		this.serviceOpportunity = serviceOpportunity;
		this.jsonWebToken = jsonWebToken;
	}

	@GET
	@Path("/list")
	@RolesAllowed({ "user", "manager" })
	public Response listAll() {
		return Response.ok().entity(serviceOpportunity.findAll()).build();
	}

}
