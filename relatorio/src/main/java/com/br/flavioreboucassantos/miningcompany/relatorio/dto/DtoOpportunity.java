package com.br.flavioreboucassantos.miningcompany.relatorio.dto;

import java.math.BigDecimal;

public final record DtoOpportunity(
		long idProposal,
		String customer,
		BigDecimal priceTonne,
		BigDecimal lastCurrencyQuotation) {
}
