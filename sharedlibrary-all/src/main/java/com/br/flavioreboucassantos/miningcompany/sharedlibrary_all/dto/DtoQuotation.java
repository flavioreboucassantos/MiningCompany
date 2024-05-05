package com.br.flavioreboucassantos.miningcompany.sharedlibrary_all.dto;

import java.math.BigDecimal;

public final record DtoQuotation(long timestamp, BigDecimal currencyPrice, BigDecimal pctChange, String pair) {
}
