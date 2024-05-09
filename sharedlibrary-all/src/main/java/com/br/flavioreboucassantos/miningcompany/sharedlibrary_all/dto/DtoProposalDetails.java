package com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto;

import java.math.BigDecimal;

public final record DtoProposalDetails(
		Long idProposal,
		String customer,
		BigDecimal priceTonne,
		int tonnes,
		String country,
		int proposalValidityDays) {
}
