package it.univaq.disim.mwt.letsjamrestapi.services;

import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.AuthLoginBody;
import it.univaq.disim.mwt.letsjamrestapi.models.NewUser;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public abstract class AuthApiService {
    public abstract Response addUser(NewUser body, SecurityContext securityContext, UriInfo uriInfo) throws NotFoundException;

    public abstract Response login(AuthLoginBody body, SecurityContext securityContext, UriInfo uriInfo) throws NotFoundException;

    public abstract Response logout(SecurityContext securityContext, ContainerRequestContext req) throws NotFoundException;

    public abstract Response refreshToken(SecurityContext securityContext, ContainerRequestContext req) throws NotFoundException;
}
