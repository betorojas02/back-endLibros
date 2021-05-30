package com.credibanco.assessment.library.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.credibanco.assessment.library.api.client.exceptions.ExcepcionesCliente;
import com.credibanco.assessment.library.model.Autor;
import com.credibanco.assessment.library.model.Editorial;
import com.credibanco.assessment.library.service.AutorService;

/**
 * Controlador para ver agregar eliminar y editar autores
 * @author Jusus Humberto Moreno Rojas
 *
 */
@RestController
@RequestMapping("/autor")
public class AutorController  extends ExcepcionesCliente{ 

	
	@Autowired
	private AutorService autorService;
	
	
	/**
	 * 
	 * @return lista de todos los autores
	 */
	@GetMapping
	public ResponseEntity<?> allEditorial(){
		
		
		try {
			return ResponseEntity.ok().body(autorService.findAll());
		} catch (Exception e) {

			Map<String, String> error = new HashMap<String, String>();

			error.put("message", "Error en la peticion");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
		}
	}
	
	/**
	 * guardar el autor con sus validaciones
	 * @param Autor autor
	 * @param retorna en autor guardado
	 * @return
	 */
	@PostMapping("/save")
	public ResponseEntity<?> saveAutor(@Valid @RequestBody Autor autor , BindingResult result){
		
		
		//valida la entreda de datos
		if(result.hasErrors()) {
			return this.validar(result);
		}
		Autor autorDb = autorService.save(autor);
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(autorDb);
		
	}
	
	
	/**
	 * buscar por nombre de autores
	 * @param name
	 * @return lista de autores dependiendo de la variable de entrada
	 */
	@GetMapping(params  = "name")
	public ResponseEntity<?> nameFilter(@RequestParam( name = "name") String name){
		try {
			return ResponseEntity.ok().body(autorService.findAutorByName(name));
		} catch (Exception e) {

			Map<String, String> error = new HashMap<String, String>();

			error.put("message", "Error en la peticion");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
		}
	}
	
	
	/**
	 * Actualiza un autor
	 * @param autor
	 * @param result
	 * @param id
	 * @return el autor actualizado
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> updateAutor(@Valid @RequestBody Autor autor , BindingResult result, @PathVariable Long id){
		
		
		// valida la  entrada de datos
		if(result.hasErrors()) {
			return this.validar(result);
		}
		// valida que el autor exista
		Optional<Autor> autorlDb = autorService.findByIdAutor(id);
		if (!autorlDb.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Autor autorSave = autorlDb.get();
		
		autorSave.setCiudadProcedencia(autor.getCiudadProcedencia());
		autorSave.setCorreoElectronico(autor.getCorreoElectronico());
		autorSave.setFechaNacimiento(autor.getFechaNacimiento());
		autorSave.setNombreCompleto(autor.getNombreCompleto());
		
		
		Autor autorDb = autorService.save(autor);
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(autorDb);
		
	}
	
	

	/**
	 * elimana un autor
	 * @param id
	 * @return si fue existoso la eliminacion
	 */
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAutor(@PathVariable Long id){
		try {
			 autorService.deleteById(id);
		}catch (Exception e) {
			Map<String, String> error = new HashMap<String, String>();

			error.put("message", "Error en la peticion");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
		}
		return ResponseEntity.noContent().build();
		
	}
}
