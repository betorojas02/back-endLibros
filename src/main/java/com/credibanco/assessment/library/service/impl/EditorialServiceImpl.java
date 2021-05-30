package com.credibanco.assessment.library.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credibanco.assessment.library.model.Editorial;
import com.credibanco.assessment.library.repository.EditorialRepository;
import com.credibanco.assessment.library.service.EditorialService;


@Service
public class EditorialServiceImpl implements EditorialService {

	@Autowired
	private EditorialRepository editorialRepository;
	
	
	public Iterable<Editorial> findAll() {
	
	return editorialRepository.findAll();
	}
	
	 public Editorial save(Editorial editorial) {
		// TODO Auto-generated method stub
		return editorialRepository.save(editorial);
	}
	 
	 @Override
	public Optional<Editorial> findByIdEditorial(Long id) {
		// TODO Auto-generated method stub
		return editorialRepository.findById(id);
	}
	 
	 @Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		  editorialRepository.deleteById(id);
	}

}
