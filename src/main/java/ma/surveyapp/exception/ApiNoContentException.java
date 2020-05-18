package ma.surveyapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class ApiNoContentException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ApiNoContentException(String message) {
		super(message);
	}

}
