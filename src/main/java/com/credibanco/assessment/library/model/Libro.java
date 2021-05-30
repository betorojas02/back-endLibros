package com.credibanco.assessment.library.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="libros")
@TableGenerator(
	    name = "LibroTable", 
	    initialValue = 1, 
	    pkColumnName = "ENTITY", 
	    pkColumnValue = "ID", 
	    allocationSize = 10, 
	    table = "ENTITY_GENERATOR"
	)
public class Libro  implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LibroTable")
	private Long id;
	
	@NotEmpty
	@NotNull
	private String titulo;


	private int year;
	@NotEmpty
	@NotNull
	private String genero;
	

	@NotNull
	private int numeroDePaginas;
	
	
	 /**
     * Autores.
     */
	@NotNull
	@ManyToOne
	@JoinColumn(name = "autor")
	    private Autor Autor;
    /**
     * Editorial.
     */

	@JsonIgnoreProperties(value = {"libros","applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "editorial" )

    private Editorial editorial;
	
	


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public int getNumeroDePaginas() {
		return numeroDePaginas;
	}
	public void setNumeroDePaginas(int numeroDePaginas) {
		this.numeroDePaginas = numeroDePaginas;
	}

	
	public Autor getAutor() {
		return Autor;
	}
	public void setAutor(Autor autor) {
		Autor = autor;
	}
	public Editorial getEditorial() {
		return editorial;
	}
	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}
    
	@Override
	public boolean equals(Object obj) {

		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof Libro)) {
			return false;
		}

		Libro a = (Libro) obj;
		
		return this.id.equals(a.getId()) && this.id != null;
	}
	

}
