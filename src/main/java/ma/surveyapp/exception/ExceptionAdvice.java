package ma.surveyapp.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class ExceptionAdvice {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ApiBadRequestException.class) 
	public ResponseEntity<Object> handlerBadRequestException(ApiBadRequestException e){
		ExceptionReponse exceptionReponse = new ExceptionReponse(e.getMessage(),LocalDateTime.now(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(exceptionReponse, exceptionReponse.getHttpStatus());
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ApiNotFoundException.class)
	public ResponseEntity<Object> handlerNotFoundException(ApiNotFoundException e){
		ExceptionReponse exceptionReponse = new ExceptionReponse(e.getMessage(),LocalDateTime.now(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(exceptionReponse, exceptionReponse.getHttpStatus());
	}
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(ApiConflictException.class)
	public ResponseEntity<Object> handlerConflictException(ApiConflictException e){
		ExceptionReponse exceptionReponse = new ExceptionReponse(e.getMessage(),LocalDateTime.now(), HttpStatus.CONFLICT);
		return new ResponseEntity<>(exceptionReponse, exceptionReponse.getHttpStatus());
	}
	
	@ResponseStatus(HttpStatus.NOT_MODIFIED)
	@ExceptionHandler(ApiNotModifiedException.class)
	public ResponseEntity<Object> handlerNotModifiedException(ApiNotModifiedException e){
		ExceptionReponse exceptionReponse = new ExceptionReponse(e.getMessage(),LocalDateTime.now(), HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<>(exceptionReponse, exceptionReponse.getHttpStatus());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ExceptionHandler(ApiNoContentException.class)
	public ResponseEntity<Object> handlerNotContentException(ApiNoContentException e){
		ExceptionReponse exceptionReponse = new ExceptionReponse(e.getMessage(),LocalDateTime.now(), HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(exceptionReponse, exceptionReponse.getHttpStatus());
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handlerSeveralException(Exception e){
		ExceptionReponse exceptionReponse = new ExceptionReponse(e.getMessage(),LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(exceptionReponse, exceptionReponse.getHttpStatus());
	}

}
