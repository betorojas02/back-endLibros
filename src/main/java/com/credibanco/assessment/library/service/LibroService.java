package com.credibanco.assessment.library.service;

import java.util.List;
import java.util.Optional;



import com.credibanco.assessment.library.api.client.exceptions.ExcepcionMaximoPermitidoEditorial;

import com.credibanco.assessment.library.model.Editorial;
import com.credibanco.assessment.library.model.Libro;

public interface LibroService {

	public Iterable<Libro> findAll();
	
	public Libro save (Libro libro, Editorial editorial) throws ExcepcionMaximoPermitidoEditorial;
	
	public List<Libro>  findAutorByTitulo(String titulo);
	public List<Libro>  findByYearContainingIgnoreCase(int year);
	
	public void deleteById(Long id);
	
	public Optional<Libro> findById(Long id);
}
