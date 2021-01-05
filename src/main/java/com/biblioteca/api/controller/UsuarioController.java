package com.biblioteca.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.api.model.Usuario;
import com.biblioteca.api.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<Usuario> createUsuario(Usuario usuario) {
		usuarioService.salvarUsuario(usuario);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/infoUser")
	public UserDetails getUsuario(@AuthenticationPrincipal UserDetails user) {
		
		return user;
	}
	
	@GetMapping("/{login}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<Usuario> getUsuarioPorLogin(@PathVariable("login") String login) {
		Usuario usuario = usuarioService.buscarPorLogin(login);
		return ResponseEntity.ok(usuario);
	}
	
}
