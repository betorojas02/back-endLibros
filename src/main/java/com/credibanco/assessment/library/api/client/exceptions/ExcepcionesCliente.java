package com.credibanco.assessment.library.api.client.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class ExcepcionesCliente {

	
	
	protected ResponseEntity<?> validar(BindingResult result) {
		
		 Map<String, Object> errores = new HashMap();
		
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), "El campo " + err.getField() + " "+ err.getDefaultMessage());
		});
		
			return ResponseEntity.badRequest().body(errores);
	
	}
}
