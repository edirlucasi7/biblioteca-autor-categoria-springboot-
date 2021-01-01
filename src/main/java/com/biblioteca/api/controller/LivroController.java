package com.biblioteca.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.api.model.Livro;
import com.biblioteca.api.service.LivroService;

@RestController
@RequestMapping("/livro")
public class LivroController {

	@Autowired
	private LivroService livroService;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Livro> create(@RequestBody Livro livro) {
		Livro livroCriado = livroService.createLivro(livro);
		return ResponseEntity.ok(livroCriado);
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Livro>> listarLivros() {
		List<Livro> livros = livroService.allLivro();
		return ResponseEntity.ok(livros);
	}
	
	@GetMapping("/listarLivro/{id}")
	public ResponseEntity<Livro> listarLivro(@PathVariable("id")Long id) { 
		Livro listaLivro = livroService.listLivro(id);
		
		if(listaLivro == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(listaLivro);
	}
	
	@DeleteMapping("/{id}")
	public String deleteLivro(@PathVariable("id") Long id) {
		livroService.deleteLivro(id);
		
		return "Livro deletado com sucesso";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
