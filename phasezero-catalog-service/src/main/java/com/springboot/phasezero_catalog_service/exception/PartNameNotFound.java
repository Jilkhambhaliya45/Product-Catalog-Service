package com.springboot.phasezero_catalog_service.exception;

public class PartNameNotFound extends RuntimeException {
	public PartNameNotFound(String message) {
		super(message);
	}
}
