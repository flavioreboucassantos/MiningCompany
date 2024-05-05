package com.br.flavioreboucassantos.miningcompany.cotacao.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public final record DtoUsdBrl(
		String code,
		String codein,
		String name,
		BigDecimal high,
		BigDecimal low,
		BigDecimal varBid,
		BigDecimal pctChange,
		BigDecimal bid,
		BigDecimal ask,
		long timestamp,
		@JsonProperty("create_date") String createDate) {
}
