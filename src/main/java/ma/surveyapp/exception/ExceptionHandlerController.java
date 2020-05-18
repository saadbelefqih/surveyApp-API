package ma.surveyapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(ApiBadRequestException.class)
	public ResponseEntity<Object> handlerBadRequestException(ApiBadRequestException e){
		ExceptionReponse exceptionReponse = new ExceptionReponse(e.getMessage(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(exceptionReponse, exceptionReponse.getHttpStatus());
	}
	
	@ExceptionHandler(ApiNotFoundException.class)
	public ResponseEntity<Object> handlerNotFoundException(ApiNotFoundException e){
		ExceptionReponse exceptionReponse = new ExceptionReponse(e.getMessage(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(exceptionReponse, exceptionReponse.getHttpStatus());
	}
	
	@ExceptionHandler(ApiConflictException.class)
	public ResponseEntity<Object> handlerConflictException(ApiConflictException e){
		ExceptionReponse exceptionReponse = new ExceptionReponse(e.getMessage(), HttpStatus.CONFLICT);
		return new ResponseEntity<>(exceptionReponse, exceptionReponse.getHttpStatus());
	}
	
	@ExceptionHandler(ApiNotModifiedException.class)
	public ResponseEntity<Object> handlerNotModifiedException(ApiNotModifiedException e){
		ExceptionReponse exceptionReponse = new ExceptionReponse(e.getMessage(), HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<>(exceptionReponse, exceptionReponse.getHttpStatus());
	}
	
	@ExceptionHandler(ApiNoContentException.class)
	public ResponseEntity<Object> handlerNotModifiedException(ApiNoContentException e){
		ExceptionReponse exceptionReponse = new ExceptionReponse(e.getMessage(), HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(exceptionReponse, exceptionReponse.getHttpStatus());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handlerSeveralException(Exception e){
		ExceptionReponse exceptionReponse = new ExceptionReponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(exceptionReponse, exceptionReponse.getHttpStatus());
	}

}
