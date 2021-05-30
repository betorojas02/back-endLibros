package com.credibanco.assessment.library.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



@Entity
@Table(name="autor")
@TableGenerator(
	    name = "AutorlTable", 
	    initialValue = 1, 
	    pkColumnName = "ENTITY", 
	    pkColumnValue = "ID", 
	    allocationSize = 10, 
	    table = "ENTITY_GENERATOR"
	)
public class Autor {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AutorlTable")
	private Long id;
	
	@NotEmpty
	@NotNull
	private String nombreCompleto;
	private String fechaNacimiento;
	
	private String ciudadProcedencia;
	
	@Email
	private String correoElectronico;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCiudadProcedencia() {
		return ciudadProcedencia;
	}

	public void setCiudadProcedencia(String ciudadProcedencia) {
		this.ciudadProcedencia = ciudadProcedencia;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	} 

	
	@Override
	public boolean equals(Object obj) {

		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof Autor)) {
			return false;
		}

		Autor a = (Autor) obj;
		
		return this.id.equals(a.getId()) && this.id != null;
	}
	
	
	
}
