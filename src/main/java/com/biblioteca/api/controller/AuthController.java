package com.biblioteca.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.security.sasl.AuthenticationException;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.api.config.JwtTokenProvider;
import com.biblioteca.api.model.Role;
import com.biblioteca.api.model.Usuario;
import com.biblioteca.api.service.UsuarioService;
import com.biblioteca.api.util.AuthenticationRequest;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping
public class AuthController{

	@Autowired
	AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    private UsuarioService usuarioService;
	
    @PostMapping("/login")
    public ResponseEntity signin(@RequestBody AuthenticationRequest data) throws AuthenticationException {

        String username = data.getUsername();
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
		
		List<String> roleString = new ArrayList<>();
		
		Usuario usuario = usuarioService.buscarPorLogin(username);
		
		List<Role> roles = usuario.getRoles();
		
		String token = jwtTokenProvider.createToken(username, roleString);
		
		Map<Object, Object> model = new HashMap<>();
		model.put("username", username);
		model.put("roles", roles);
		model.put("token", "Bearer " + token);
		return ok(model);
    }
    
}
