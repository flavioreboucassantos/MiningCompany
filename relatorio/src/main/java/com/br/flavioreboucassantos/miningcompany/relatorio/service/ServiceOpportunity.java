package com.br.flavioreboucassantos.miningcompany.relatorio.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.br.flavioreboucassantos.miningcompany.relatorio.dto.DtoOpportunity;
import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto.DtoProposal;
import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto.DtoQuotation;
import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.service.ServiceBase;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ServiceOpportunity extends ServiceBase {

	void createOpportunity(final DtoProposal dtoProposal);

	void createQuotation(final DtoQuotation dtoQuotation);

	List<DtoOpportunity> findAll();

	ByteArrayInputStream generateCSVOpportunityReport();

}
