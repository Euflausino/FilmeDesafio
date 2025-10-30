package com.bradesco.capacitacao_filmes.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.bradesco.capacitacao_filmes.dto.FilmeCadastroDTO;
import com.bradesco.capacitacao_filmes.dto.FilmeMapper;
import com.bradesco.capacitacao_filmes.entities.Filme;
import com.bradesco.capacitacao_filmes.repository.FilmeRepository;

@Configuration // executa a aplicaçao em contexto especifico, conforme no app.properts
@Profile("test") //executa a classe sempre que logado no perfil de teste no .properts
public class FilmeConfig implements CommandLineRunner{

	private final FilmeRepository repository;

    public FilmeConfig(FilmeRepository repository) {
        this.repository = repository;
    }

	@Override //permite mocar dados, para persistirem mesmo com banco de dados em memoria.
	public void run(String... args) {
		// TODO Auto-generated method stub
		
		FilmeCadastroDTO filme1 = new FilmeCadastroDTO("Como treinar seu dragão", LocalDate.of(2020,2,19), 5, "Aventura", "Robert");
		FilmeCadastroDTO filme2 = new FilmeCadastroDTO("Velozes e furiosos", LocalDate.of(2018,3,19), 4, "Ação", "Autor um");
		FilmeCadastroDTO filme3 = new FilmeCadastroDTO("Superman", LocalDate.of(2023,2,8), 3, "Ação", "Autor dois");
		FilmeCadastroDTO filme4 = new FilmeCadastroDTO("Filme legal01", LocalDate.of(2000,4,16), 2, "Romance", "Autor um");
		FilmeCadastroDTO filme5 = new FilmeCadastroDTO("Filme Legal", LocalDate.of(2010,2,6), 1, "Comedia", "Robert");
		
		Filme filme21 = FilmeMapper.toEntity(filme1);
		Filme filme22 = FilmeMapper.toEntity(filme2);
		Filme filme23 = FilmeMapper.toEntity(filme3);
		Filme filme24 = FilmeMapper.toEntity(filme4);
		Filme filme25 = FilmeMapper.toEntity(filme5);
		
		repository.saveAll(Arrays.asList(filme21, filme22, filme23, filme24, filme25));
		
	}
	
}
