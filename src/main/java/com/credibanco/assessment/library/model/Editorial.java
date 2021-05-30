package com.credibanco.assessment.library.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "editorial")
@TableGenerator(
	    name = "EditorialTable", 
	    initialValue = 1, 
	    pkColumnName = "ENTITY", 
	    pkColumnValue = "ID", 
	    allocationSize = 10, 
	    table = "ENTITY_GENERATOR"
	)
public class Editorial {

	




	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EditorialTable")
	private Long id;
	


	private String nombre;

	@NotEmpty
	@NotNull
	private String dirrecionCorrespondencia;

	@NotEmpty
	@NotNull
	private String telefono;
	
	@Email
	private String email;
	
	private int maximoLibrosRegistrados;
	
	
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@PrePersist
	public void prePersit() {	
		System.out.println("pre persist " + this.getMaximoLibrosRegistrados());
		int maximolibros = this.getMaximoLibrosRegistrados();
		
		
		if (maximolibros == 0) {
			this.maximoLibrosRegistrados = -1;
		}else {
			this.maximoLibrosRegistrados = this.getMaximoLibrosRegistrados();
		}
			
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDirrecionCorrespondencia() {
		return dirrecionCorrespondencia;
	}

	public void setDirrecionCorrespondencia(String dirrecionCorrespondencia) {
		this.dirrecionCorrespondencia = dirrecionCorrespondencia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getMaximoLibrosRegistrados() {
		return maximoLibrosRegistrados;
	}

	public void setMaximoLibrosRegistrados(int maximoLibrosRegistrados) {
		this.maximoLibrosRegistrados = maximoLibrosRegistrados;
	}
	@Override
	public boolean equals(Object obj) {

		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof Editorial)) {
			return false;
		}

		Editorial a = (Editorial) obj;
		
		return this.id.equals(a.getId()) && this.id != null;
	}
	
	
}
