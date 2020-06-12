package ma.surveyapp.exception;

public class ApiInternalServerErrorExeption extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApiInternalServerErrorExeption(String message) {
		super(message);
	}

}
