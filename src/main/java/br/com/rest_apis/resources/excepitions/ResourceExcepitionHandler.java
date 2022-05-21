package br.com.rest_apis.resources.excepitions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import br.com.rest_apis.services.excepitions.ObjectNotFoundExcepition;

@ControllerAdvice
public class ResourceExcepitionHandler {
	
	@ExceptionHandler(ObjectNotFoundExcepition.class)
	public ResponseEntity<StandardError>objectNotFound(ObjectNotFoundExcepition ex,HttpServletRequest request){
		StandardError error = 
				new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());
	 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

}
