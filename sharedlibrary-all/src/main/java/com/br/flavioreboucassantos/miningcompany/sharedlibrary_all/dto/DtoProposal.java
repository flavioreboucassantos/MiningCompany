package com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto;

import java.math.BigDecimal;

public final record DtoProposal(long idProposal, String customer, BigDecimal priceTonne) {
}
