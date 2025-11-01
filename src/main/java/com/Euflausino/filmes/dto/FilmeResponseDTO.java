package com.Euflausino.filmes.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public record FilmeResponseDTO (
		
		Long id,
		
		String titulo,
		
		@JsonFormat(pattern ="dd/MM/yyyy")
		LocalDate dataLancamento,
		
		Integer nota,
		
		String genero,
		
		String diretor,
		
		
		String classificacao
		
		){
	
}
