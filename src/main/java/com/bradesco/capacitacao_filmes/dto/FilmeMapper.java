package com.bradesco.capacitacao_filmes.dto;

import java.util.List;
import java.util.Optional;

import com.bradesco.capacitacao_filmes.entities.Filme;
import com.bradesco.capacitacao_filmes.repository.FilmeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class FilmeMapper {
	
	private static FilmeRepository filmeRepository;

    public FilmeMapper(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public static Filme toEntity(FilmeCadastroDTO dto) {
		Filme filmeSave = new Filme();
		filmeSave.setTitulo(dto.titulo());
		filmeSave.setDataLancamento(dto.dataLancamento());
		filmeSave.setNota(dto.nota());
		filmeSave.setGenero(dto.genero());
		filmeSave.setDiretor(dto.diretor());
		return filmeSave;
	}
	
	public static FilmeResponseDTO dtoResponse(Filme toEntity) {

        return new FilmeResponseDTO(
				toEntity.getId(),
				toEntity.getTitulo(),
				toEntity.getDataLancamento(),
				toEntity.getNota(),
				toEntity.getGenero(),
				toEntity.getDiretor(),
				toEntity.getClassificacao()
				);
	}
	
	public static Page<FilmeResponseDTO> toResponseDTOList(List<Filme> filmes) {
	    List<FilmeResponseDTO> retorno = filmes.stream()
	                 .map(FilmeMapper::dtoResponse).toList();
        return new PageImpl<>(retorno);
	}
	
}
