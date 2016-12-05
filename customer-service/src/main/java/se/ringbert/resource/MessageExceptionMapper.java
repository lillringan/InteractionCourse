package se.ringbert.resource;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MessageExceptionMapper implements ExceptionMapper<InvalidCustomerException>{

	@Override
	public Response toResponse(InvalidCustomerException exception) {
		return Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).build();
	}

}