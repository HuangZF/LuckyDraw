package com.ruyicai.draw.exception;

public class RuyicaiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RuyicaiException(String msg) {
		super(msg);
	}
	
	public RuyicaiException(String msg, Throwable e) {
		super(msg, e);
	}
	
}
