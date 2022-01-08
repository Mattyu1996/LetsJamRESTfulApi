package it.univaq.disim.mwt.letsjamrestapi.factories;

import javax.annotation.Generated;

import it.univaq.disim.mwt.letsjamrestapi.services.UsersApiService;
import it.univaq.disim.mwt.letsjamrestapi.services.impl.UsersApiServiceImpl;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class UsersApiServiceFactory {
    private final static UsersApiService service = new UsersApiServiceImpl();

    public static UsersApiService getUsersApi() {
        return service;
    }
}
