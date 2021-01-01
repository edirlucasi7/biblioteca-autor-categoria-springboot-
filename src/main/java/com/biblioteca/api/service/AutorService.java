package com.biblioteca.api.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.biblioteca.api.model.Autor;
import com.biblioteca.api.repository.AutorRepository;

@Service
public class AutorService {

	@Autowired
	private AutorRepository autorRepository;
	
	public Autor createAutor(Autor autor) {
		return autorRepository.save(autor);
	}
	
	public Map<String, Object> findByIdAutor(Long id) {
		return autorRepository.informacoesAutor(id);
	}
	
	public List<Map<String, Object>> findByPorNome(String nome) {
		return autorRepository.findAllNome(nome);
	}
	
	public Optional<Autor> findByAutorOptional(Long id) {

		Assert.notNull(id, "Testando");
		
		Optional<Autor> exisOptional = autorRepository.findById(id);
		
		if (exisOptional.isPresent()) {
			return exisOptional;
		}
		
		return null;
	}
	
	public List<Autor> listaAllAutor() {
		return autorRepository.findAll();
	}
	
	public Autor updateAutor(Long id, Autor autor) {
//		Assert.notNull(id, "Não foi possível atualizar o registro");

		Optional<Autor> existeOptional = findByAutorOptional(id);
		
		if(existeOptional.isPresent()) {
			Autor existeAutor = existeOptional.get();
			existeAutor.setNome(autor.getNome());
			autorRepository.save(existeAutor);
			
			return existeAutor;
		} 
		
		return null;
		
	}
	
	public void deleteAutor(Long id) {
		autorRepository.deleteById(id);
	}
	
}
