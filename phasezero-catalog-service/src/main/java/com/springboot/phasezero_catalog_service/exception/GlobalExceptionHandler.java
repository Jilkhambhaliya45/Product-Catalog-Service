package com.springboot.phasezero_catalog_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.phasezero_catalog_service.dto.ResponseStructure;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(PartNameNotFound.class)
	public ResponseEntity<ResponseStructure<String>> PartNameNotFound(PartNameNotFound partNameNotFound) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setMessage("Fail");
		responseStructure.setData(partNameNotFound.getMessage());
		responseStructure.setStatusCode(HttpStatus.CONFLICT.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(PartNumberAlreadyExists.class)
	public ResponseEntity<ResponseStructure<String>> partNumberAlreadyExistsExcpetion(
			PartNumberAlreadyExists partNumberAlreadyExists) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setMessage("Already there");
		responseStructure.setData(partNumberAlreadyExists.getMessage());
		responseStructure.setStatusCode(HttpStatus.CONFLICT.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ObjectIsNotValid.class)
	public ResponseEntity<ResponseStructure<String>> partNameAlreadyExists(ObjectIsNotValid objectIsNotValid) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setMessage("Pass All the fields");
		responseStructure.setData(objectIsNotValid.getMessage());
		responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NegativeValueException.class)
	public ResponseEntity<ResponseStructure<String>> NegativeValueExceptionHandler(
			NegativeValueException negativeValueException) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setMessage("The value is negative");
		responseStructure.setData(negativeValueException.getMessage());
		responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoProductRecordFoundException.class)
	public ResponseEntity<ResponseStructure<String>> NoProductRecordFoundExceptionHandler(
			NoProductRecordFoundException noProductRecordFoundException) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		responseStructure.setMessage("No Any Product Record Found");
		responseStructure.setData(noProductRecordFoundException.getMessage());
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}
}
