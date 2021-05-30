package com.credibanco.assessment.library.service;

import java.util.List;
import java.util.Optional;

import com.credibanco.assessment.library.model.Autor;

public interface AutorService {
	public Iterable<Autor> findAll();
	
	public Optional<Autor> findByIdAutor(Long id);
	
	public Autor save (Autor autor);
	
	public List<Autor>  findAutorByName(String nombre);
	public void deleteById(Long id);
}
