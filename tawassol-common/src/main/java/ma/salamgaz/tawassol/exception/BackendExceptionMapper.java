package ma.salamgaz.tawassol.exception;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import ma.salamgaz.tawassol.common.exception.BackendException;

public class BackendExceptionMapper implements ExceptionMapper<BackendException> {

	@Override
	public Response toResponse(BackendException exception) {
		ErrorMessage errorMessage = new ErrorMessage();
		
		errorMessage.setDebugMessage(exception.getDebugMessage());
		errorMessage.setErrorCode(exception.getErrorCode().getInternalCode());
		errorMessage.setHttpStatus(exception.getErrorCode().getHttpCode());
		errorMessage.setErrorId(exception.getErrorId());
		errorMessage.setUserMessage(exception.getUserMessage());
		
		
		return Response.status(errorMessage.getHttpStatus())
				.entity(errorMessage)
				.type(MediaType.APPLICATION_JSON)
				.build();	
		
	}

}
