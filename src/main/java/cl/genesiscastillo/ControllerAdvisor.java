package cl.genesiscastillo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import cl.genesiscastillo.exception.ErrorBussinesException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestControllerAdvice
public class ControllerAdvisor {

	@ExceptionHandler(ErrorBussinesException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public ErrorResponseGeneric handleErrorBussinesException(ErrorBussinesException ex, WebRequest request) {
		return new ErrorResponseGeneric(ex.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponseGeneric handleExceptionGeneric(Exception ex, WebRequest request) {
		return new ErrorResponseGeneric(ex.getMessage());
	}
	
	@Getter
	@RequiredArgsConstructor
	public static class ErrorResponseGeneric {
		private final String message;
	}

}
