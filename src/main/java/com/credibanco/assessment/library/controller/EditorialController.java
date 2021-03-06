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
import org.springframework.web.bind.annotation.RestController;

import com.credibanco.assessment.library.api.client.exceptions.ExcepcionesCliente;
import com.credibanco.assessment.library.model.Editorial;
import com.credibanco.assessment.library.service.EditorialService;
/**
 * 
 * controllador de editorial con sus metodos PU, GET POST , DELETE
 * @author Jesus humberto Moreno Rojas
 *
 */
@RestController
@RequestMapping("/editorial")
public class EditorialController  extends ExcepcionesCliente{
	
	
	@Autowired
	private EditorialService editorialService;
	
	
	/**
	 * 
	 * @return lista todas las editoriales
	 */
	@GetMapping
	public ResponseEntity<?> allEditorial(){
		
		
		try {
			return ResponseEntity.ok().body(editorialService.findAll());
		} catch (Exception e) {

			Map<String, String> error = new HashMap<String, String>();

			error.put("message", "Error en la peticion");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
		}
	}
	
	/**
	 * 
	 * metodo para guardar una nueva editorial
	 * @param editorial
	 * @param result
	 * @return retorna la editorial creada
	 */
	@PostMapping("/save")
	public ResponseEntity<?> saveEditorial(@Valid @RequestBody Editorial editorial , BindingResult result){
		
		// valida datos de entrada
		if(result.hasErrors()) {
			return this.validar(result);
		}
		try {
			Editorial editorialdB = editorialService.save(editorial);
			return ResponseEntity.status(HttpStatus.CREATED).body(editorialdB);
		}catch (Exception e) {
			Map<String, String> error = new HashMap<String, String>();

			error.put("message", "Error en la peticion");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
		}
		
		
		
	
	}
	
	/**
	 * actualiza una editorial
	 * @param editorial
	 * @param result
	 * @param id
	 * @return la editorial actualizas
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEditorial(@Valid @RequestBody Editorial editorial , BindingResult result, @PathVariable Long id){
		
		
		//valida datos de entrada
		if(result.hasErrors()) {
			return this.validar(result);
		}
		try {
			
			//valida si existe una editorial
			Optional<Editorial> editorialDb = editorialService.findByIdEditorial(id);
			if (!editorialDb.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			
			Editorial editorialSave = editorialDb.get();
			
			editorialSave.setDirrecionCorrespondencia(editorial.getDirrecionCorrespondencia());
			editorialSave.setEmail(editorial.getEmail());
			editorialSave.setMaximoLibrosRegistrados(editorial.getMaximoLibrosRegistrados());
			editorialSave.setNombre(editorial.getNombre());
			editorialSave.setTelefono(editorial.getTelefono());
			
			
			
			Editorial editorialdB = editorialService.save(editorialSave);
			return ResponseEntity.status(HttpStatus.CREATED).body(editorialdB);
		}catch (Exception e) {
			Map<String, String> error = new HashMap<String, String>();

			error.put("message", "Error en la peticion");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
		}
	}
	
	
	
	/**
	 * elimina una editorial 
	 * @param id
	 * @return is fue exitoso la eliminacion de al editorial
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEditorial(@PathVariable Long id){
		try {
			 editorialService.deleteById(id);
		}catch (Exception e) {
			Map<String, String> error = new HashMap<String, String>();

			error.put("message", "Error en la peticion");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
		}
		return ResponseEntity.noContent().build();
		
	}
	
}
