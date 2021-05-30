package com.credibanco.assessment.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.credibanco.assessment.library.model.Editorial;


@Repository
public interface EditorialRepository extends CrudRepository<Editorial, Long> {  }


