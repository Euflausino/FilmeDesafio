package com.bradesco.capacitacao_filmes.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.bradesco.capacitacao_filmes.service.filme.FilmeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bradesco.capacitacao_filmes.dto.filme.FilmeCadastroDTO;
import com.bradesco.capacitacao_filmes.dto.filme.FilmeMapper;
import com.bradesco.capacitacao_filmes.dto.filme.FilmeResponseDTO;
import com.bradesco.capacitacao_filmes.entities.Filme;
import com.bradesco.capacitacao_filmes.repository.FilmeRepository;

@ExtendWith(MockitoExtension.class)
class FilmeServiceTest {

	@Mock
	private FilmeRepository repository;
	
	@InjectMocks
	private FilmeService service;
	
	@Test
	void deveListarTodosUsuarios() {
		List<FilmeCadastroDTO> filmes = Arrays.asList(
				
				new FilmeCadastroDTO("Como treinar seu dragão", LocalDate.of(2020,02,19), 5, "Aventura", "Robert"),
				new FilmeCadastroDTO("Velozes e furiosos", LocalDate.of(2018,03,19), 4, "Ação", "Autor um"),
				new FilmeCadastroDTO("Superman", LocalDate.of(2023,02,8), 3, "Ação", "Autor dois"),
				new FilmeCadastroDTO("Filme legal01", LocalDate.of(2000,04,16), 2, "Romance", "Autor um"),
				new FilmeCadastroDTO("Filme Legal", LocalDate.of(2010,02,6), 1, "Comedia", "Robert")
				
				);
		List<FilmeResponseDTO> retorno = FilmeMapper.toEntity(filmes);
		
		// quando chamarmos o repositorio deve retornar a lista filmes
		Mockito.when(repository.findAll()).thenReturn(filmes);
		
		//chama o metodo que sera testado
		List<Filme> resultado = service.listarFilmes();
		
		//verifica se o resultado e igual a lista
		assertThat(resultado).isEqualTo(filmes);
		
	}

}
