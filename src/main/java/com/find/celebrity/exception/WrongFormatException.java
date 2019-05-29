package com.find.celebrity.exception;

public class WrongFormatException extends Exception{

	private static final long serialVersionUID = -6775848144453623305L;

	private String extension;
	
	public WrongFormatException(String extension) {
		this.extension = extension;
	}
	
	@Override
	public String getMessage() {
		return "File extension ["+extension+"] not allowed";
	}
}
