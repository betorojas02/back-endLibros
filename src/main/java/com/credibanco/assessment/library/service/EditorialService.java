package com.credibanco.assessment.library.service;

import java.util.Optional;


import com.credibanco.assessment.library.model.Editorial;

public interface EditorialService {
	public Iterable<Editorial> findAll();
	public Optional<Editorial> findByIdEditorial(Long id);
	public Editorial save (Editorial editorial);
	public void deleteById(Long id);
	
}
