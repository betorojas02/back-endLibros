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

import com.credibanco.assessment.library.api.client.exceptions.ExcepcionMaximoPermitidoEditorial;

import com.credibanco.assessment.library.api.client.exceptions.ExcepcionesCliente;
import com.credibanco.assessment.library.model.Autor;
import com.credibanco.assessment.library.model.Editorial;
import com.credibanco.assessment.library.model.Libro;

import com.credibanco.assessment.library.service.AutorService;
import com.credibanco.assessment.library.service.EditorialService;
import com.credibanco.assessment.library.service.LibroService;

/**
 * controllador de libro con sus metodos PU, GET POST , DELETE
 * @author Jesus humberto Moreno Rojas
 *
 */
@RestController
@RequestMapping("/libro")
public class LibroController extends ExcepcionesCliente {

	@Autowired
	private LibroService libroService;
	@Autowired
	private AutorService autorService;

	@Autowired
	private EditorialService editorialService;

	/**
	 * 
	 * @return lista todos los libros
	 */
	@GetMapping
	public ResponseEntity<?> allEditorial() {

		try {
			return ResponseEntity.ok().body(libroService.findAll());
		} catch (Exception e) {

			Map<String, String> error = new HashMap<String, String>();

			error.put("message", "Error en la peticion");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
		}
	}

	/**
	 * guarda y valida la creacion de un libro
	 * @param libro
	 * @param result
	 * @return retorna el libro creado
	 * @throws ExcepcionMaximoPermitidoEditorial
	 */
	@PostMapping("/save")
	public ResponseEntity<?> saveEditorial(@Valid @RequestBody Libro libro, BindingResult result)
			throws ExcepcionMaximoPermitidoEditorial {

		//se valida que exista una editorial
		Optional<Editorial> EditorialDb = null;
		//se valida los campos de entrada
		if (result.hasErrors()) {
			return this.validar(result);
		}
		Map<String, String> error = new HashMap<String, String>();

		//se valida si existe un autor
		Optional<Autor> autorDb = autorService.findByIdAutor(libro.getAutor().getId());
		if (libro.getEditorial() != null) {
			EditorialDb = editorialService.findByIdEditorial(libro.getEditorial().getId());
			if (!EditorialDb.isPresent()) {
				error.put("Editorial", "La editorial no está registrada");
			}
		}

		if (!autorDb.isPresent()) {
			error.put("Autor", "El autor no está registrado");
		}
		if (error.size() > 0)
			return ResponseEntity.badRequest().body(error);

		try {
			Libro librodB = libroService.save(libro, EditorialDb.get());
			return ResponseEntity.status(HttpStatus.CREATED).body(librodB);
		} catch (ExcepcionMaximoPermitidoEditorial e) {

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	@GetMapping(params = "titulo")
	public ResponseEntity<?> findTitulo(@RequestParam( name = "titulo") String titulo){
		
		
		

		try {
			return ResponseEntity.ok().body(libroService.findAutorByTitulo(titulo));
		} catch (Exception e) {

			Map<String, String> error = new HashMap<String, String>();

			error.put("message", "Error en la peticion");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
		}
	}
	
	/**
	 * retorna los libros por buscada de año
	 * @param year
	 * @return 
	 */
	@GetMapping(params = "year")
	public ResponseEntity<?> findTitulo(@RequestParam( name = "year") int year){
		
		
	
		try {
			return ResponseEntity.ok().body(libroService.findByYearContainingIgnoreCase(year));
		} catch (Exception e) {

			Map<String, String> error = new HashMap<String, String>();

			error.put("message", "Error en la peticion");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
		}
	}
	
	
	/**
	 * valida y actualiza un libro
	 * @param libro
	 * @param result
	 * @param id
	 * @return el libro actualizado
	 * @throws ExcepcionMaximoPermitidoEditorial
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Libro libro, BindingResult result,@PathVariable Long id)
		throws ExcepcionMaximoPermitidoEditorial {

			Optional<Editorial> EditorialDb = null;
			if (result.hasErrors()) {
				return this.validar(result);
			}
			Map<String, String> error = new HashMap<String, String>();

			Optional<Autor> autorDb = autorService.findByIdAutor(libro.getAutor().getId());
			if (libro.getEditorial() != null) {
				EditorialDb = editorialService.findByIdEditorial(libro.getEditorial().getId());
				if (!EditorialDb.isPresent()) {
					error.put("Editorial", "La editorial no está registrada");
				}
			}

			if (!autorDb.isPresent()) {
				error.put("Autor", "El autor no está registrado");
			}
			if (error.size() > 0)
				return ResponseEntity.badRequest().body(error);

			try {
				
				
				Optional<Libro> libroDb = libroService.findById(id);
				if (!libroDb.isPresent()) {
					return ResponseEntity.notFound().build();
				}
				
				Libro libroGuardar = libroDb.get();
				
				//se obtiene el libro de la db y se cambia por los datos de la peticion
				
				libroGuardar.setGenero(libro.getGenero());
				libroGuardar.setTitulo(libro.getTitulo());
				libroGuardar.setYear(libro.getYear());
				libroGuardar.setAutor(libro.getAutor());
				if (EditorialDb.isPresent()) libroGuardar.setEditorial(libro.getEditorial());
				libroGuardar.setNumeroDePaginas(libro.getNumeroDePaginas());
				
				
				
				Libro librodB = libroService.save(libroGuardar, EditorialDb.get());
				return ResponseEntity.status(HttpStatus.CREATED).body(librodB);
			} catch (ExcepcionMaximoPermitidoEditorial e) {

				return ResponseEntity.badRequest().body(e.getMessage());
			}
	}
	
	/**
	 * elimina un libro
	 * @param id
	 * @return si fue exitoso la eliminacion de un libro
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLibro(@PathVariable Long id){
		
		try {
			 libroService.deleteById(id);
		}catch (Exception e) {
			Map<String, String> error = new HashMap<String, String>();

			error.put("message", "Error en la peticion");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(error);
		}
		return ResponseEntity.noContent().build();
		
	}

}
