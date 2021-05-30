package com.credibanco.assessment.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.credibanco.assessment.library.model.Autor;

@Repository
public interface AutorRepository extends CrudRepository<Autor, Long> {

	
//	@Query(nativeQuery=true , value="select * FROM autor a where UPPER(a.nombre_completo)  LIKE UPPER('%:nombre%') ")
//	@Query("Select a from Autor a where a.nombreCompleto LIKE '%'||:nombre||'%'")
	@Query("Select c from Autor c where lower(c.nombreCompleto) like lower(concat('%', concat(:nombre, '%')))")
	public List<Autor>  findByNombreCompletoContainingIgnoreCase(@Param("nombre") String nombre);
 
	
}

