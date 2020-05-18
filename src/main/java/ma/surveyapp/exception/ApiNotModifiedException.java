package ma.surveyapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class ApiNotModifiedException extends Exception {

	private static final long serialVersionUID = 1L;

	public ApiNotModifiedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
