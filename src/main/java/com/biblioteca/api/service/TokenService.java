package com.biblioteca.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.api.model.Token;
import com.biblioteca.api.repository.TokenRepository;

@Service
public class TokenService {

	@Autowired
	private TokenRepository tokenRepository;
	
	public void salvarToken(Token token) {
		tokenRepository.save(token);
	}
	
}
