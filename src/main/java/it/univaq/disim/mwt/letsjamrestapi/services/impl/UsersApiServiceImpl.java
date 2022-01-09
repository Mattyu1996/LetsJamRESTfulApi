package it.univaq.disim.mwt.letsjamrestapi.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import it.univaq.disim.mwt.letsjamrestapi.business.services.UserDBService;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.User;
import it.univaq.disim.mwt.letsjamrestapi.services.UsersApiService;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class UsersApiServiceImpl extends UsersApiService {

    @Override
    public Response getAllUsers(String email, String username, String role, SecurityContext securityContext)
            throws NotFoundException {

        List<User> result = new ArrayList<User>();

        if (email != null && !email.isEmpty()) {
            // getUserByEmail
        } else if (username != null && !username.isEmpty()) {
            // getUserByUsername
        } else if (role != null && !role.isEmpty()) {
            // getUserByRole
        } else {
            result = UserDBService.getAllUsers();
        }

        return Response.ok().entity(result).build();
    }
}
