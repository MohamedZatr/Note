package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javassist.NotFoundException;

@ControllerAdvice
public class MyExceptionHandler {

	
	@ExceptionHandler(NotfoundResult.class)
	public final ResponseEntity<ErrorMessage> notFound(NotfoundResult exception){
		ErrorMessage errorMessage = new ErrorMessage(
				exception.getMessage(),
				HttpStatus.NOT_FOUND.value()
				);
		return new   ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ExceptionFiledRequired.class)
	public final ResponseEntity<Object> somethingWentWrong(ExceptionFiledRequired e){
		ErrorMessage errorMessage =  new ErrorMessage(
					e.getMessage(),
					HttpStatus.BAD_REQUEST.value()
				);
		return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST); 
	}
	
}
