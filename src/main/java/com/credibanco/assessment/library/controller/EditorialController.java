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
//
@RestController
@RequestMapping("/editorial")
public class EditorialController  extends ExcepcionesCliente{
	
	
	@Autowired
	private EditorialService editorialService;
	
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
	
	@PostMapping("/save")
	public ResponseEntity<?> saveEditorial(@Valid @RequestBody Editorial editorial , BindingResult result){
		
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
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEditorial(@Valid @RequestBody Editorial editorial , BindingResult result, @PathVariable Long id){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		try {
			
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
