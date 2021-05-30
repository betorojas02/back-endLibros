package com.credibanco.assessment.library.service.impl;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credibanco.assessment.library.model.Autor;
import com.credibanco.assessment.library.repository.AutorRepository;
import com.credibanco.assessment.library.service.AutorService;


@Service
public class AutorServiceImpl implements AutorService {

	
	@Autowired
	private AutorRepository autorRepository;
	
	@Override
	public Iterable<Autor> findAll() {
		// TODO Auto-generated method stub
		return autorRepository.findAll();
	}

	@Override
	public Autor save(Autor autor) {
		// TODO Auto-generated method stub
		return autorRepository.save(autor);
	}
	
	@Override
	public  Optional<Autor> findByIdAutor(Long id) {
		// TODO Auto-generated method stub
		return autorRepository.findById(id);
	}
	@Override
	public List<Autor> findAutorByName(String nombre) {
		// TODO Auto-generated method stub
		System.out.println(nombre);
		return autorRepository.findByNombreCompletoContainingIgnoreCase(nombre);
	}
@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		autorRepository.deleteById(id);
	}

}
