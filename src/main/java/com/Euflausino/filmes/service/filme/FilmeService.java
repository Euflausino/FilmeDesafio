package com.Euflausino.filmes.service.filme;

import java.time.LocalDate;

import com.Euflausino.filmes.dto.filme.AtualizarFilmeDTO;
import com.Euflausino.filmes.dto.filme.FilmeCadastroDTO;
import com.Euflausino.filmes.mapper.filme.FilmeMapper;
import com.Euflausino.filmes.dto.filme.FilmeResponseDTO;
import com.Euflausino.filmes.exception.filme.FilmeNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.Euflausino.filmes.entities.filme.Filme;
import com.Euflausino.filmes.repository.filme.FilmeRepository;

@Service
public class FilmeService {
	
	@Autowired
	private FilmeRepository repository;
	
	public Page<FilmeResponseDTO> listarFilmes(){
		return FilmeMapper.toResponsePageDTO(repository.findAll());
	}
	
	public FilmeResponseDTO cadastrarUmFilme(FilmeCadastroDTO filme) {
        Filme filmeEntity = FilmeMapper.toEntity(filme);
		repository.save(filmeEntity);
        return FilmeMapper.dtoResponse(filmeEntity);
	}
	
	public void deletarFilme(Long id) {
		validarFilme(id);
        repository.deleteById(id);
	}
	
	public Page<FilmeResponseDTO> buscarPorDiretor(String diretor){

        return FilmeMapper.toResponsePageDTO(repository.findByDiretorContainingIgnoreCase(diretor));
	}
	
	public FilmeResponseDTO buscarPorId(Long id){
        return FilmeMapper.dtoResponse(validarFilme(id));
	}
	
	public Page<FilmeResponseDTO> buscarPorNome(String titulo){
		return FilmeMapper.toResponsePageDTO(repository.findByTituloContainingIgnoreCase(titulo));
	}
	
	public Page<FilmeResponseDTO> buscarPorGenero(String genero){
		return FilmeMapper.toResponsePageDTO(repository.findByGeneroContainingIgnoreCase(genero));
	}
	
	public Page<FilmeResponseDTO> buscarPorNota(Integer nota){
		return FilmeMapper.toResponsePageDTO(repository.findByNota(nota));
	}
	
	public Page<FilmeResponseDTO> buscarPorData(LocalDate dataInicio, LocalDate dataFim){
		return FilmeMapper.toResponsePageDTO(repository.findByDataLancamentoBetweenOrderByNotaDesc(dataInicio, dataFim));
	}

    public FilmeResponseDTO atualizarFilme(Long id, AtualizarFilmeDTO filmeAtualizado) {
        Filme filme = validarFilme(id);
        if (filmeAtualizado.titulo() != null) {filme.setTitulo(filmeAtualizado.titulo());}
        if(filmeAtualizado.dataLancamento() != null){filme.setDataLancamento(filmeAtualizado.dataLancamento());}
        if (filmeAtualizado.nota() != null) {filme.setNota(filmeAtualizado.nota());}
        if(filmeAtualizado.genero() != null) {filme.setGenero(filmeAtualizado.genero());}
        if(filmeAtualizado.diretor() != null) {filme.setDiretor(filmeAtualizado.diretor());}
        return FilmeMapper.dtoResponse(filme);
    }

    private Filme validarFilme(Long id) {
        return repository.findById(id).orElseThrow(() -> new FilmeNaoEncontradoException("Filme não encontrado."));
    }
	
}
