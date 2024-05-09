package com.br.flavioreboucassantos.miningcompany.gateway_bff.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.br.flavioreboucassantos.miningcompany.gateway_bff.client.RestClientReport;
import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto.DtoOpportunity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public final class ImplServiceReport implements ServiceReport {

	private final RestClientReport restClientReport;

	@Inject
	public ImplServiceReport(@RestClient final RestClientReport restClientReport) {
		this.restClientReport = restClientReport;
	}

	@Override
	public List<DtoOpportunity> findAll() {
		return restClientReport.listAll();
	}

	@Override
	public ByteArrayInputStream generateCSVOpportunityReport() {
		return opportunitiesToCSV(restClientReport.listAll());
	}

}
