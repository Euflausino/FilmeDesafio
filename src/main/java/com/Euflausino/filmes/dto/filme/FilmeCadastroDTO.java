package com.Euflausino.filmes.dto.filme;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record FilmeCadastroDTO(
			
		@NotBlank
		String titulo,
		
		@PastOrPresent
		@JsonFormat(pattern ="dd/MM/yyyy")
		LocalDate dataLancamento,
		
		@NotNull
		@Min(1)
		@Max(5)
		Integer nota,
		
		@NotBlank
		String genero,
		
		@NotBlank
		String diretor

		
		) {
	
	
	
}
