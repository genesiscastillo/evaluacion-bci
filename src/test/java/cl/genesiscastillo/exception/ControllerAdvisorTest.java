package cl.genesiscastillo.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.context.request.WebRequest;

import cl.genesiscastillo.ControllerAdvisor;

public class ControllerAdvisorTest {
	
	@Test
	public void mock() {
		Exception ex = Mockito.mock(Exception.class);
		WebRequest request = Mockito.mock(WebRequest.class );
		
		ControllerAdvisor advisor = new ControllerAdvisor();
		ControllerAdvisor.ErrorResponseGeneric response = advisor.handleExceptionGeneric(ex, request);
		
		Assertions.assertNotNull(response);
	}
	
	@Test
	public void mockHandleConflictSaveUserByEmailException() {
		
		ErrorBussinesException ex = Mockito.mock(ErrorBussinesException.class );
		WebRequest request = Mockito.mock(WebRequest.class);
		
		ControllerAdvisor advisor = new ControllerAdvisor();
		ControllerAdvisor.ErrorResponseGeneric response = advisor.handleErrorBussinesException(ex, request);
		
		Assertions.assertNotNull(response);
		Assertions.assertNull(response.getMessage());
		
	}

}
