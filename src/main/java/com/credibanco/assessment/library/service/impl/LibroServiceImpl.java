package com.credibanco.assessment.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credibanco.assessment.library.api.client.exceptions.ExcepcionMaximoPermitidoEditorial;
import com.credibanco.assessment.library.model.Editorial;
import com.credibanco.assessment.library.model.Libro;
import com.credibanco.assessment.library.repository.LibroRepository;
import com.credibanco.assessment.library.service.LibroService;

@Service
public class LibroServiceImpl implements LibroService {

	@Autowired
	private LibroRepository libroRepository;
	
	
	@Override
	public Iterable<Libro> findAll() {
		// TODO Auto-generated method stub
		return libroRepository.findAll();
	}
	
	@Override
	public Libro save(Libro libro , Editorial editorial)  throws ExcepcionMaximoPermitidoEditorial{
		// TODO Auto-generated method stub
				
		if(editorial != null ) {
			int totalEditoriales = libroRepository.findByIdEditorialCount(libro.getEditorial().getId());
			
			System.out.println("total en bd " + totalEditoriales + " maximo libros registrados "  +  editorial.getMaximoLibrosRegistrados());
			if(!(editorial.getMaximoLibrosRegistrados() == -1)  ) {
				if(totalEditoriales >= editorial.getMaximoLibrosRegistrados()) 	throw new ExcepcionMaximoPermitidoEditorial("No es posible registrar el libro, se alcanzó el máximo permitido.");
			}
		
			
			
		}
		
		return libroRepository.save(libro);
	}
	
	@Override
	public List<Libro> findAutorByTitulo(String titulo) {
	
		return libroRepository.findByTituloContainingIgnoreCase(titulo);
	}
	
	@Override
	public List<Libro> findByYearContainingIgnoreCase(int year) {
		// TODO Auto-generated method stub
		return libroRepository.findByYearContainingIgnoreCase(year);
	}
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
	libroRepository.deleteById(id);
		
	}
	
	@Override
	public Optional<Libro> findById(Long id) {
		// TODO Auto-generated method stub
		return libroRepository.findById(id);
	}
	
	
}
