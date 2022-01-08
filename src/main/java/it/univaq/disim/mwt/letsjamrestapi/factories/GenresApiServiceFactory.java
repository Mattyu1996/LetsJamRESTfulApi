package it.univaq.disim.mwt.letsjamrestapi.factories;

import javax.annotation.Generated;

import it.univaq.disim.mwt.letsjamrestapi.services.GenresApiService;
import it.univaq.disim.mwt.letsjamrestapi.services.impl.GenresApiServiceImpl;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class GenresApiServiceFactory {
    private final static GenresApiService service = new GenresApiServiceImpl();

    public static GenresApiService getGenresApi() {
        return service;
    }
}
