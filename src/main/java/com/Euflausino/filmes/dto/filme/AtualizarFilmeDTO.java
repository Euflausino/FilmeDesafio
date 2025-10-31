package com.Euflausino.filmes.dto.filme;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record AtualizarFilmeDTO(

        String titulo,

        @JsonFormat(pattern ="dd/MM/yyyy")
        LocalDate dataLancamento,

        Integer nota,

        String genero,

        String diretor


        ) {
}
