package com.credibanco.assessment.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.credibanco.assessment.library.model.Libro;


@Repository
public interface LibroRepository extends CrudRepository<Libro, Long> {
	
	@Query(nativeQuery=true , value="select COUNT(*) FROM libros l where l.editorial =?1 ")
	public int  findByIdEditorialCount(Long id);
	
//	@Query(nativeQuery=true , value="select * FROM libros a where UPPER(a.titulo)  LIKE UPPER('%:aa%') ")
	@Query("Select l from Libro l where lower(l.titulo) like lower(concat('%', concat(:titulo, '%')))")
	public List<Libro>  findByTituloContainingIgnoreCase(@Param("titulo") String titulo);
	
	
	@Query("Select l from Libro l where lower(l.year) like lower(concat('%', concat(:year, '%')))")
	public List<Libro>  findByYearContainingIgnoreCase(@Param("year") int year);

}
