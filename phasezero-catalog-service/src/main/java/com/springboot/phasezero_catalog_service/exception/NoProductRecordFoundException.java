package com.springboot.phasezero_catalog_service.exception;

public class NoProductRecordFoundException extends RuntimeException {
	public NoProductRecordFoundException(String message) {
		super(message);
	}
}
