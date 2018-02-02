package ma.salamgaz.tawassol.common.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class CustomWebApplicationException extends WebApplicationException {

    private static final long serialVersionUID = -6531995599692084729L;

    public CustomWebApplicationException(ErrorMessageType error) {
        super(Response.status(error.getHttpResponseStatus())
                .entity(new ErrorMessageResponse(error.getCode(), error.getFieldName(), error.getMessage()))
                .type(MediaType.APPLICATION_JSON).build());
    }

    public CustomWebApplicationException(Response.Status status, ErrorMessageResponse error) {
        super(Response.status(status).entity(error).type(MediaType.APPLICATION_JSON).build());
    }

    public CustomWebApplicationException(ErrorMessageMapper error) {
        super(Response.status(error.getError().getHttpResponseStatus())
                .entity(new ErrorMessageResponse(error.getError().getCode(), error.getError().getFieldName(),
                        error.getError().getMessage()))
                .type(MediaType.APPLICATION_JSON).build());
    }
}
