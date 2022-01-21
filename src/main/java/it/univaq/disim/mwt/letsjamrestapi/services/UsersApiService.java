package it.univaq.disim.mwt.letsjamrestapi.services;

import java.sql.SQLException;

import javax.annotation.Generated;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public abstract class UsersApiService {
    public abstract Response getAllUsers(String email,  String username,  String role, SecurityContext securityContext) throws NotFoundException, ApiException;
}
