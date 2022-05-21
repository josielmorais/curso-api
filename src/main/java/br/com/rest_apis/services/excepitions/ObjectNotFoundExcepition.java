package br.com.rest_apis.services.excepitions;

public class ObjectNotFoundExcepition extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundExcepition(String message) {
		super(message);
		
		
	}

}
