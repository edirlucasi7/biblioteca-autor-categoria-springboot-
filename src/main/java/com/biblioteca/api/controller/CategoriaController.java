package com.biblioteca.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.api.model.Categoria;
import com.biblioteca.api.service.CategoriaService;

//@CrossOrigin(origins="*")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Categoria> create(@RequestBody Categoria categoria) {
		Categoria categoriaCriada = categoriaService.createCategoria(categoria);
		return ResponseEntity.ok(categoriaCriada);
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Categoria>> listAllCategoria() {
		List<Categoria> listCategoria = categoriaService.listCategorias();
		return ResponseEntity.ok(listCategoria);
	}
}
