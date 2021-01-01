package com.biblioteca.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	private String nome;
	
	private int quantidade_paginas;
	
	@ManyToOne
	@JoinColumn(name = "autor_id")
	private Autor autor_id;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria_id;

}
