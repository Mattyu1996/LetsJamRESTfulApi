package it.univaq.disim.mwt.letsjamrestapi.services.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.User;
import it.univaq.disim.mwt.letsjamrestapi.models.User.RoleEnum;
import it.univaq.disim.mwt.letsjamrestapi.services.UsersApiService;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class UsersApiServiceImpl extends UsersApiService {
    @Override
    public Response getAllUsers(SecurityContext securityContext) throws NotFoundException {

        List<User> utenti = new ArrayList<User>();
        User a = new User();
        a.setFirstname("Mario");
        a.setLastname("Rossi");
        a.setRole(RoleEnum.UTENTE);
        utenti.add(a);

        return Response.ok().entity(utenti).build();
    }
}
