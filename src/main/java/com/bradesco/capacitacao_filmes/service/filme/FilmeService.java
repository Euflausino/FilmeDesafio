package com.bradesco.capacitacao_filmes.service.filme;

import java.time.LocalDate;

import com.bradesco.capacitacao_filmes.dto.filme.AtualizarFilmeDTO;
import com.bradesco.capacitacao_filmes.dto.filme.FilmeCadastroDTO;
import com.bradesco.capacitacao_filmes.dto.filme.FilmeMapper;
import com.bradesco.capacitacao_filmes.dto.filme.FilmeResponseDTO;
import com.bradesco.capacitacao_filmes.exception.filme.FilmeNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.bradesco.capacitacao_filmes.entities.Filme;
import com.bradesco.capacitacao_filmes.repository.FilmeRepository;

@Service
public class FilmeService {
	
	@Autowired
	private FilmeRepository repository;
	
	public Page<FilmeResponseDTO> listarFilmes(){
		return FilmeMapper.toResponseDTOList(repository.findAll());
	}
	
	public FilmeResponseDTO cadastrarUmFilme(FilmeCadastroDTO filme) {
        Filme filmeEntity = FilmeMapper.toEntity(filme);
		repository.save(filmeEntity);
        return FilmeMapper.dtoResponse(filmeEntity);
	}
	
	public Filme atualizarFilme(Long id, Filme filmeAtualizado) {
		if(repository.existsById(id)) {
			filmeAtualizado.setId(id);
			return repository.save(filmeAtualizado);
		}
		return null;
	}
	
	public void deletarFilme(Long id) {
		validarFilme(id);
        repository.deleteById(id);
	}
	
	public Page<FilmeResponseDTO> buscarPorDiretor(String diretor){

        return FilmeMapper.toResponseDTOList(repository.findByDiretorContainingIgnoreCase(diretor));
	}
	
	public FilmeResponseDTO buscarPorId(Long id){
        return FilmeMapper.dtoResponse(validarFilme(id));
	}
	
	public Page<FilmeResponseDTO> buscarPorNome(String titulo){
		return FilmeMapper.toResponseDTOList(repository.findByTituloContainingIgnoreCase(titulo));
	}
	
	public Page<FilmeResponseDTO> buscarPorGenero(String genero){
		return FilmeMapper.toResponseDTOList(repository.findByGeneroContainingIgnoreCase(genero));
	}
	
	public Page<FilmeResponseDTO> buscarPorNota(Integer nota){
		return FilmeMapper.toResponseDTOList(repository.findByNota(nota));
	}
	
	public Page<FilmeResponseDTO> buscarPorData(LocalDate dataInicio, LocalDate dataFim){
		return FilmeMapper.toResponseDTOList(repository.findByDataLancamentoBetweenOrderByNotaDesc(dataInicio, dataFim));
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
