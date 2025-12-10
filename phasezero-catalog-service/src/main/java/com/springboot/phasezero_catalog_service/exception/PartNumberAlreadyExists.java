package com.springboot.phasezero_catalog_service.exception;

public class PartNumberAlreadyExists extends RuntimeException {
	public PartNumberAlreadyExists(String message) {
		super(message);
	}
}
