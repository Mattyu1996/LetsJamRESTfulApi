package it.univaq.disim.mwt.letsjamrestapi.services;

import javax.annotation.Generated;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public abstract class UsersApiService {
    public abstract Response getAllUsers(SecurityContext securityContext) throws NotFoundException;
}
