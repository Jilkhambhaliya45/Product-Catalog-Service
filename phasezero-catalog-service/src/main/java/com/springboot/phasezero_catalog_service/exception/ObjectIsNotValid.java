package com.springboot.phasezero_catalog_service.exception;

public class ObjectIsNotValid extends RuntimeException {
	public ObjectIsNotValid(String message) {
		super(message);
	}
}
