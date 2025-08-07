package com.exceptionsAndValidation.controller;

import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exceptionsAndValidation.model.Brand;
import com.exceptionsAndValidation.model.Product;
import com.exceptionsAndValidation.model.ReturnResponse;
import com.exceptionsAndValidation.service.ServiceClass;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/excep")
public class ControllerClass
{
	/** constructor injection **/
	private ServiceClass serviceClass;

	ControllerClass(ServiceClass serviceClass)
	{
		this.serviceClass = serviceClass;
	}

	@PostMapping("/saveBrand")
	public ReturnResponse saveBrand(@RequestBody Brand brand)
	{
		ReturnResponse response = new ReturnResponse();
		try
		{
			return serviceClass.saveBrand(brand);
		} catch (NullPointerException ne)
		{
			response.setExceptionDetails("Null value not allowed", ne);
		} catch (ConstraintViolationException cve)
		{
			response.setExceptionDetails("Constraint Violated", cve);
		} catch (DataIntegrityViolationException die)
		{
			response.setExceptionDetails(die.getCause().getMessage(), die);
		} catch (Exception e)
		{
			response.setExceptionDetails("Some exception occur:",e);
		}
		return response;
	}

	//handle exception globally using @ControllerAdvise and @ExceptionHandler
	@PostMapping("/savePdt")
	public ResponseEntity<ReturnResponse> savePdt(@RequestBody Product product)
	{
		ReturnResponse response = new ReturnResponse();
		Product savedProduct =  serviceClass.saveProduct(product);
		response.setEntityResponse("Product saved successfully.", savedProduct);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//Using spring boot validation
	@PostMapping("/saveNewPdt")
	public ResponseEntity<ReturnResponse> saveNewPdt(@Valid @RequestBody Product product)
	{
		ReturnResponse response = new ReturnResponse();
		Product savedProduct =  serviceClass.saveProduct(product);
		response.setEntityResponse("Product saved successfully.", savedProduct);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
