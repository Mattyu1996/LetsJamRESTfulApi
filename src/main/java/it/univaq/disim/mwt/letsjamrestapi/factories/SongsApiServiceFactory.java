package it.univaq.disim.mwt.letsjamrestapi.factories;

import javax.annotation.Generated;
import it.univaq.disim.mwt.letsjamrestapi.services.SongsApiService;
import it.univaq.disim.mwt.letsjamrestapi.services.impl.SongsApiServiceImpl;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class SongsApiServiceFactory {
    private final static SongsApiService service = new SongsApiServiceImpl();

    public static SongsApiService getSongsApi() {
        return service;
    }
}
