package br.com.rest_apis.services.excepitions;

public class DataIntegratyViolationExcepition extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataIntegratyViolationExcepition(String message) {
		super(message);
		
		
	}

}
