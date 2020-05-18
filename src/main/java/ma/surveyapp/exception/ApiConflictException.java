package ma.surveyapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ApiConflictException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ApiConflictException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
