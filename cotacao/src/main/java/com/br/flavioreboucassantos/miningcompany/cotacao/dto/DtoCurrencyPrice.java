package com.br.flavioreboucassantos.miningcompany.cotacao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public final record DtoCurrencyPrice(@JsonProperty("USDBRL") DtoUsdBrl dtoUsdBrl) {
}