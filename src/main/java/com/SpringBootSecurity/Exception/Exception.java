package com.SpringBootSecurity.Exception;

import java.util.HashMap;
import java.util.Map;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Exception {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
public Map<String, String> handleException(MethodArgumentNotValidException ex) {
		
		Map<String, String> erroArgs = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(index->{
		
			erroArgs.put(index.getField(), index.getDefaultMessage());
		});
		
		return erroArgs;
	}
	

	
}
