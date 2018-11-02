package com.main.util;

public class ItemNotFoundException extends RuntimeException {

	/**
	 * @author Amol Suryavanshi
	 */ 
	
	private static final long serialVersionUID = 7948988832252888146L;

	public ItemNotFoundException(String message){
		super(message);
	}

}
