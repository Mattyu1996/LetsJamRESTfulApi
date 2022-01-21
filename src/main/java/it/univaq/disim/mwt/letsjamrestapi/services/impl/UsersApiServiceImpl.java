package it.univaq.disim.mwt.letsjamrestapi.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.univaq.disim.mwt.letsjamrestapi.business.services.UserDBService;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.models.User;
import it.univaq.disim.mwt.letsjamrestapi.services.UsersApiService;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class UsersApiServiceImpl extends UsersApiService {

    @Override
    public Response getAllUsers(String email, String username, String role, SecurityContext securityContext)
            throws ApiException {

        List<User> result = new ArrayList<User>();

        try {
            if (email != null && !email.isEmpty()) {
                result.add(UserDBService.getUserByEmail(email));
            } else if (username != null && !username.isEmpty()) {
                result.add(UserDBService.getUserByUsername(username));
            } else if (role != null && !role.isEmpty()) {
                result = UserDBService.getUserByRole(role);
            } else {
                result = UserDBService.getAllUsers();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApiException(500);
        }

        return Response.ok().entity(result).build();
    }
}
