package com.Euflausino.filmes.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.Euflausino.filmes.entities.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long>{
	
	List<Filme> findByTituloContainingIgnoreCase(String nome);
	List<Filme> findByDiretorContainingIgnoreCase(String diretor);
	List<Filme> findByGeneroContainingIgnoreCase(String genero);
	List<Filme> findByNota(Integer nota);
	List<Filme> findByDataLancamentoBetweenOrderByNotaDesc(LocalDate dataInicio, LocalDate dataFim);
}
