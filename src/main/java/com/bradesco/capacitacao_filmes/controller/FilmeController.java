package com.bradesco.capacitacao_filmes.controller;

import java.time.LocalDate;

import com.bradesco.capacitacao_filmes.dto.filme.AtualizarFilmeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bradesco.capacitacao_filmes.dto.filme.FilmeCadastroDTO;
import com.bradesco.capacitacao_filmes.dto.filme.FilmeResponseDTO;
import com.bradesco.capacitacao_filmes.service.filme.FilmeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/filmes")
public class FilmeController {

    
	
	@Autowired
	private FilmeService service;

	@GetMapping(value="/listar")
	public ResponseEntity<Page<FilmeResponseDTO>> listarTodos(){
		return ResponseEntity.ok(service.listarFilmes());
	}
	
	@PostMapping(value="/cadastrar")
	public ResponseEntity<FilmeResponseDTO> cadastrarFilme(@RequestBody @Valid FilmeCadastroDTO dto){

			return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarUmFilme(dto));
		
	}
	
	@PutMapping(value="/atualizar/{id}")
	public ResponseEntity<FilmeResponseDTO> atualizarFilme(@PathVariable Long id, @RequestBody @Valid AtualizarFilmeDTO filmeAtualizado){
			return ResponseEntity.ok().body(service.atualizarFilme(id, filmeAtualizado));
		
	}
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<Void> deletarFilme(@PathVariable Long id) {
		
		service.deletarFilme(id);
        return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping(value="/busca-por-diretor")
	public ResponseEntity<Page<FilmeResponseDTO>> buscarPorDiretor(@RequestParam String diretor){

			return ResponseEntity.ok().body(service.buscarPorDiretor(diretor));
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<FilmeResponseDTO> buscarPorId(@PathVariable Long id){
		 return  ResponseEntity.ok().body(service.buscarPorId(id));
	}
	
	@GetMapping(value="/buscar-por-titulo")
	public ResponseEntity<Page<FilmeResponseDTO>> buscarPorTitulo(@RequestParam String titulo){
		 return ResponseEntity.ok().body(service.buscarPorNome(titulo));
	}
	
	@GetMapping(value="/buscar-por-genero")
	public ResponseEntity<Page<FilmeResponseDTO>> buscarPorGenero(@RequestParam String genero){
		return ResponseEntity.ok().body(service.buscarPorGenero(genero));
	}
	
	@GetMapping(value="/buscar-por-nota/{nota}")
	public ResponseEntity<Page<FilmeResponseDTO>> buscarPorNota(@PathVariable Integer nota){
		return ResponseEntity.ok().body(service.buscarPorNota(nota));
	}
	
	@GetMapping(value="/buscar-por-data")
	public ResponseEntity<Page<FilmeResponseDTO>> buscarPorData(@RequestParam LocalDate inicio, @RequestParam LocalDate fim){
		return ResponseEntity.ok().body(service.buscarPorData(inicio, fim));
	}
}
