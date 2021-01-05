package com.biblioteca.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.biblioteca.api.model.Usuario;
import com.biblioteca.api.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario buscarPorLogin(String login) {
		return usuarioRepository.findByLogin(login);
	}
	
	public void salvarUsuario(Usuario usuario) {
		
		if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
			usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		} else {
			usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		}
		
		usuarioRepository.save(usuario);
		
	}

}
