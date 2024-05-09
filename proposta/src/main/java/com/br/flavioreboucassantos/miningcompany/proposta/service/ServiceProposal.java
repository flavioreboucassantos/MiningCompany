package com.br.flavioreboucassantos.miningcompany.proposta.service;

import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto.DtoProposalDetails;
import com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.service.ServiceBase;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ServiceProposal extends ServiceBase {

	DtoProposalDetails findDtoProposalDetails(final long idProposal);

	void createProposal(final DtoProposalDetails dtoProposalDetails);

	void removeProposal(final long idProposal);

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
	 * @param id
	 * @return
	 */
	DtoProposalDetails tryFindDtoProposalDetails(final long idProposal);

}
