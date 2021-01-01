package com.biblioteca.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.api.model.Livro;
import com.biblioteca.api.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;

	public Livro createLivro(Livro livro) {
		return livroRepository.save(livro);
	}
	
	public List<Livro> allLivro() {
		return livroRepository.findAll();
	}
	
	public Livro listLivro(Long id) {
		Optional<Livro> optionalLivro = livroRepository.findById(id);
		
		if (optionalLivro.isPresent()) {
			Livro existeLivro = optionalLivro.get();
			
			return existeLivro;
		}
		
		return null;
	}
	
	public void deleteLivro(Long id) {
		Livro existeLivro = listLivro(id);
		
		if (existeLivro == null) {
			throw new RuntimeException("O registro não foi encontrado.");
		}
		
		livroRepository.deleteById(id);
	}
	
}
