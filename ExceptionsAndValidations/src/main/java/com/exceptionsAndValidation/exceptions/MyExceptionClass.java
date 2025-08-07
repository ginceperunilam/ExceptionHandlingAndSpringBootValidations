package com.exceptionsAndValidation.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.exceptionsAndValidation.InvalidPriceException;
import com.exceptionsAndValidation.model.ReturnResponse;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class MyExceptionClass
{
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ReturnResponse> handleNullPointerException(NullPointerException npe)
	{
		ReturnResponse response = new ReturnResponse();
		response.setExceptionDetails("Null Value Not allowed..", npe);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ReturnResponse> handleConstraintViolationException(ConstraintViolationException cve)
	{
		ReturnResponse response = new ReturnResponse();
		response.setExceptionDetails("Constraint Violation..", cve);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataIntegrityViolationException .class)
	public ResponseEntity<ReturnResponse> handleDataInegrityException(DataIntegrityViolationException  cve)
	{
		ReturnResponse response = new ReturnResponse();
		response.setExceptionDetails("Some fields not filled..", cve);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	//custom exception
	@ExceptionHandler(InvalidPriceException.class)
	public ResponseEntity<ReturnResponse> handleException(InvalidPriceException ip)
	{
		ReturnResponse response = new ReturnResponse();
		response.setExceptionDetails("Invalid price", ip);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	//Handle spring boot validations from entity level
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<ReturnResponse> handleValidationErrors(MethodArgumentNotValidException mae) 
		 {
//			 Map<String, String> errors = new HashMap<>();
			 ReturnResponse response = new ReturnResponse();
			 mae.getBindingResult().getFieldErrors().forEach(error -> {
				 response.setExceptionDetails(error.getField().concat(" : ").concat(error.getDefaultMessage()));
//				 errors.put(error.getField(), error.getDefaultMessage());
		        }); 	
			 return new ResponseEntity<ReturnResponse>(response, HttpStatus.BAD_REQUEST);
		 }
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ReturnResponse> handleException(Exception e)
	{
		ReturnResponse response = new ReturnResponse();
		response.setExceptionDetails("Some issue occur, pls check data..",e);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
