package com.springboot.phasezero_catalog_service.exception;

public class NegativeValueException extends RuntimeException {
	public NegativeValueException(String message) {
		super(message);
	}
}
