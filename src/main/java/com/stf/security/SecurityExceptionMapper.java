package com.stf.security;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SecurityExceptionMapper implements
		ExceptionMapper<SecurityException> {

	@Context
	HttpServletRequest request;

	@Override
	public Response toResponse(SecurityException exception) {
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}
}
