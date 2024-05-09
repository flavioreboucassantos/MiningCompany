package com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto;

import java.math.BigDecimal;

public final record DtoOpportunity(
		long idProposal,
		String customer,
		BigDecimal priceTonne,
		BigDecimal lastCurrencyQuotation) {
}
