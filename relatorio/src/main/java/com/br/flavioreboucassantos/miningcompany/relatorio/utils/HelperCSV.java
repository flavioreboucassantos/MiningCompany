package com.br.flavioreboucassantos.miningcompany.relatorio.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.br.flavioreboucassantos.miningcompany.relatorio.dto.DtoOpportunity;

public final class HelperCSV {

	static final public ByteArrayInputStream opportunitiesToCSV(List<DtoOpportunity> opportunities) {

		final CSVFormat format = CSVFormat.DEFAULT
				.builder()
				.setHeader(
						"ID Proposta", "Cliente", "Preço por Tonelada", "Melhor cotação de Moeda")
				.build();

		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {

			for (DtoOpportunity dtoOpportunity : opportunities) {
				List<String> data = Arrays
						.asList(
								String.valueOf(dtoOpportunity.idProposal()), dtoOpportunity.customer(),
								String.valueOf(dtoOpportunity.priceTonne()),
								String.valueOf(dtoOpportunity.lastCurrencyQuotation())
						);
				csvPrinter.printRecord(data);
			}

			csvPrinter.flush();

			return new ByteArrayInputStream(out.toByteArray());

		} catch (IOException e) {
			throw new RuntimeException("Fail to import data to CSV file: " + e.getMessage());
		}
	}

}
