package com.br.flavioreboucassantos.miningcompany.proposta.dto;

import java.math.BigDecimal;

public final record DtoProposalDetails(
		Long idProposal,
		String customer,
		BigDecimal priceTonne,
		int tonnes,
		String country,
		int proposalValidityDays) {
}
