package cl.genesiscastillo.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public class ControllerAdvisorTest {
	
	@Test
	public void mock() {
		Exception ex = Mockito.mock(Exception.class);
		WebRequest request = Mockito.mock(WebRequest.class );
		
		ControllerAdvisor advisor = new ControllerAdvisor();
		ResponseEntity<Object> response = advisor.handleExceptionGeneric(ex, request);
		
		Assertions.assertNotNull(response);
	}
	
	@Test
	public void mockHandleConflictSaveUserByEmailException() {
		
		DataIntegrityViolationException ex = Mockito.mock(DataIntegrityViolationException.class );
		WebRequest request = Mockito.mock(WebRequest.class);
		
		ControllerAdvisor advisor = new ControllerAdvisor();
		ResponseEntity<Object> response = advisor.handleConflictSaveUserByEmailException(ex, request);
		
		Assertions.assertNotNull(response);
		
		
	}

}
