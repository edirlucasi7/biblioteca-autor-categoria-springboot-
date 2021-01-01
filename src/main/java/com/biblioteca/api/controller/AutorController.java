package com.biblioteca.api.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.api.model.Autor;
import com.biblioteca.api.service.AutorService;

@RestController
@RequestMapping("/autor")
public class AutorController {

	@Autowired
	private AutorService autorService;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Autor> create(@RequestBody Autor autor) {
		Autor autorCriado = autorService.createAutor(autor);
		return ResponseEntity.ok(autorCriado);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> listAutorMap(@PathVariable("id") Long id) {
		return new ResponseEntity<>(autorService.findByIdAutor(id), HttpStatus.OK);
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Map<String, Object>>> listAutorPorNomeMap(@PathVariable("nome") String nome) {
		return new ResponseEntity<>(autorService.findByPorNome(nome), HttpStatus.OK);
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Autor>> listAllAutor() {
		List<Autor> autores = autorService.listaAllAutor();
		return ResponseEntity.ok(autores);
	}
	
	@GetMapping("/optional/{id}")
	public ResponseEntity<Autor> listUsuario(@PathVariable("id") Long id) {
		Optional<Autor> existeOptional = autorService.findByAutorOptional(id);
		
		return existeOptional.isPresent() ?
					ResponseEntity.ok(existeOptional.get()) :
					ResponseEntity.badRequest().build();
			
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Autor> update(@PathVariable("id") Long id, @RequestBody Autor autor) {
		autor.setId(id);

		Autor existeAutor = autorService.updateAutor(id, autor);
		
		return existeAutor != null ?
				ResponseEntity.ok(existeAutor) :
				ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		try {
			autorService.deleteAutor(id);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
}
