package com.biblioteca.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.biblioteca.api.model.Autor;
import com.biblioteca.api.model.Categoria;
import com.biblioteca.api.model.Livro;
import com.biblioteca.api.repository.AutorRepository;
import com.biblioteca.api.repository.CategoriaRepository;
import com.biblioteca.api.service.LivroService;

@SpringBootTest
class ApiApplicationTests {

	@Autowired
	LivroService livroService;
	
	@Autowired
	AutorRepository autorRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Test
	void test1() {
		
		Livro livro = new Livro();
		Long idLong = (long) 3;
		Long idCategoria = (long) 3;
		
		Autor autor = autorRepository.findById(idLong).get();
		Categoria categoria = categoriaRepository.findById(idCategoria).get();
		
		livro.setNome("Teste Unitário");
		livro.setQuantidade_paginas(230);
		livro.setAutor_id(autor);
		livro.setCategoria_id(categoria);
		
		Livro livroCriado = livroService.createLivro(livro);
		
		assertNotNull(livroCriado);
		
		Long idLong2 = livroCriado.getId();
		assertNotNull(idLong2);
		
		Livro livro2 = livroService.listLivro(idLong2);
		assertTrue(livro2 != null);
		
		livroCriado = livro2;
		assertEquals("Teste Unitário", livroCriado.getNome());
		assertEquals(230, livroCriado.getQuantidade_paginas());
		assertEquals(autor, livroCriado.getAutor_id());
		assertEquals(categoria, livroCriado.getCategoria_id());
		
		livroService.deleteLivro(livro2.getId());
		assertFalse(livroService.listLivro(idLong2) != null);
	}
	
	@Test
	void test2() {
		
	}

}
