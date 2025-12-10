package com.springboot.phasezero_catalog_service.exception;

public class PartNameAlreadyExists extends RuntimeException {
	public PartNameAlreadyExists(String message) {
		super(message);
	}

}
