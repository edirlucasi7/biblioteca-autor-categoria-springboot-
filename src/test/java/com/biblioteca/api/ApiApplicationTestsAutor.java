package com.biblioteca.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.biblioteca.api.model.Autor;
import com.biblioteca.api.service.AutorService;
import com.biblioteca.api.service.LivroService;

@SpringBootTest
class ApiApplicationTestsAutor {

	@Autowired
	LivroService livroService;
	
	@Autowired
	AutorService autorService;
	
	@Test	
	void test1() {
		Long idLong = (long) 2;
		Autor autor = new Autor();
		autor.setId(idLong);
		autor.setNome("Edir Lucas da Silva Icety Braga");
		
		Autor autorCriado = autorService.createAutor(autor);
		
		assertNotNull(autorCriado);
	
		Long id2 = autorCriado.getId();
		assertNotNull(id2);
		
		Autor autor2 = autorService.findByAutorOptional(id2).get();
		assertTrue(autor2 != null );
		
	}
	
	@Test	
	void test2() {
		String nomeString = "Testando";
		assertEquals(3, autorService.findByPorNome(nomeString).size());
		
	}

}
