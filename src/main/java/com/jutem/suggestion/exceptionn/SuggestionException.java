package com.jutem.suggestion.exceptionn;

public class SuggestionException extends RuntimeException {

	private static final long serialVersionUID = -3838078515331042336L;
	
	public SuggestionException(String message) {
		super(message);
	}
	
	public SuggestionException(Throwable e) {
		super(e);
	}

}
