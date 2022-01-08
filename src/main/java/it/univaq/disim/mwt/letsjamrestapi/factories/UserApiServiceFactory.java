package it.univaq.disim.mwt.letsjamrestapi.factories;

import javax.annotation.Generated;
import it.univaq.disim.mwt.letsjamrestapi.services.UserApiService;
import it.univaq.disim.mwt.letsjamrestapi.services.impl.UserApiServiceImpl;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class UserApiServiceFactory {
    private final static UserApiService service = new UserApiServiceImpl();

    public static UserApiService getUserApi() {
        return service;
    }
}
