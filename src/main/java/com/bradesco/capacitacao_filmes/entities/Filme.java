package com.bradesco.capacitacao_filmes.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.bradesco.capacitacao_filmes.dto.filme.FilmeCadastroDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table
public class Filme {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	private LocalDate dataLancamento;
	
	private Integer nota;
	
	private String genero;
	
	private String diretor;
	
	public Filme() {
		
	}

	public Filme(FilmeCadastroDTO dto) {
		this.titulo = dto.titulo();
		this.dataLancamento = dto.dataLancamento();
		this.nota = dto.nota();
		this.genero = dto.genero();
		this.diretor = dto.diretor();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
	
	public String getClassificacao() {
		switch (nota) {
		case 1: return "Esse é ruim demais!";
		case 2: return "Ok!";
		case 3: return "Bom!";
		case 4: return "Excelente!";
		case 5: return "Absolute cinema!";
		default:
			return null;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filme other = (Filme) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
