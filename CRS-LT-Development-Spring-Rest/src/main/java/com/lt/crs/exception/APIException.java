package com.lt.crs.exception;

public class APIException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5006069555919900616L;
	private final int code;
    private final String message;

    APIException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }
    }