package com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.service;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

public interface ServiceBase {

	/**
	 * How to Uses:
	 * 
	 * <pre>
	 * <code>
	 * Model model;
	 * if ((model = service.tryFind(id)) == null)
	 *      return service.disappointedFind().build();
	 * </code>
	 * </pre>
	 * 
	 * @return Response.ResponseBuilder with Response.Status.NOT_FOUND
	 */
	default ResponseBuilder disappointedFind() {
		return Response.status(Response.Status.NOT_FOUND);
	}

}
