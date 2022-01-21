package it.univaq.disim.mwt.letsjamrestapi.security;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;
import javax.ws.rs.ext.ExceptionMapper;

import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;

public class AppExceptionMapper implements ExceptionMapper<ApiException> {

    @Override
    public Response toResponse(ApiException e) {
        
        StatusType status = Response.Status.INTERNAL_SERVER_ERROR;
        
        switch (e.getCode()) {
            case 404:
                status = Response.Status.NOT_FOUND;
                break;
            case 401:
                status = Response.Status.BAD_REQUEST;
                break;
            case 500:
                return Response.status(status).entity("General error").type(MediaType.TEXT_PLAIN).build();      
            default:
                break;
        }

        return Response.status(status).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
    }
    
}
