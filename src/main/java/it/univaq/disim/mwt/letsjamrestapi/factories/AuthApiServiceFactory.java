package it.univaq.disim.mwt.letsjamrestapi.factories;

import javax.annotation.Generated;
import it.univaq.disim.mwt.letsjamrestapi.services.AuthApiService;
import it.univaq.disim.mwt.letsjamrestapi.services.impl.AuthApiServiceImpl;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class AuthApiServiceFactory {
    private final static AuthApiService service = new AuthApiServiceImpl();

    public static AuthApiService getAuthApi() {
        return service;
    }
}
