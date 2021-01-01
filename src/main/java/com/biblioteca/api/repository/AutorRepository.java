package com.biblioteca.api.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biblioteca.api.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{
	
	@Query(value = "select * from autor as a where a.id = :id", nativeQuery = true)
	Map<String, Object> informacoesAutor(@Param("id") Long id);
	
}
