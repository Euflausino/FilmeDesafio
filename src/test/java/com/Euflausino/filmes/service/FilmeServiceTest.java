package com.Euflausino.filmes.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Euflausino.filmes.dto.FilmeCadastroDTO;
import com.Euflausino.filmes.mapper.FilmeMapper;
import com.Euflausino.filmes.dto.FilmeResponseDTO;
import com.Euflausino.filmes.entity.Filme;
import com.Euflausino.filmes.repository.FilmeRepository;
import org.springframework.data.domain.Page;

@ExtendWith(MockitoExtension.class)
class FilmeServiceTest {

	@Mock
	private FilmeRepository repository;
	
	@InjectMocks
	private FilmeService service;
	
	@Test
	void deveListarTodosUsuarios() {
		List<FilmeCadastroDTO> filmes = Arrays.asList(
				
				new FilmeCadastroDTO("Como treinar seu dragão", LocalDate.of(2020,2,19), 5, "Aventura", "Robert"),
				new FilmeCadastroDTO("Velozes e furiosos", LocalDate.of(2018,3,19), 4, "Ação", "Autor um"),
				new FilmeCadastroDTO("Superman", LocalDate.of(2023,2,8), 3, "Ação", "Autor dois"),
				new FilmeCadastroDTO("Filme legal01", LocalDate.of(2000,4,16), 2, "Romance", "Autor um"),
				new FilmeCadastroDTO("Filme Legal", LocalDate.of(2010,2,6), 1, "Comedia", "Robert")
				
				);
        Filme filme1 = FilmeMapper.toEntity(filmes.get(0));
        Filme filme2 = FilmeMapper.toEntity(filmes.get(1));
        Filme filme3 = FilmeMapper.toEntity(filmes.get(2));
        Filme filme4 = FilmeMapper.toEntity(filmes.get(3));
        Filme filme5 = FilmeMapper.toEntity(filmes.get(4));

		List<Filme> retorno = Arrays.asList(filme1, filme2, filme3, filme4, filme5);
		
		// quando chamarmos o repositorio deve retornar a lista filmes
		Mockito.when(repository.findAll()).thenReturn(retorno);
		
		//chama o metodo que sera testado
		Page<FilmeResponseDTO> resultado = service.listarFilmes();
		
		//verifica se o resultado e igual a lista
		assertThat(resultado).isEqualTo(filmes);

	}

}
