package com.biblioteca.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.api.model.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer>{

}
