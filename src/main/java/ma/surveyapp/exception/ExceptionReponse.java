package ma.surveyapp.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class ExceptionReponse {
	private final String message;
    private final HttpStatus httpStatus;
}
