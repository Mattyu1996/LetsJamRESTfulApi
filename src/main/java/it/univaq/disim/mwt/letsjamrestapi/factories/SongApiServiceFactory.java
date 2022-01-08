package it.univaq.disim.mwt.letsjamrestapi.factories;

import javax.annotation.Generated;
import it.univaq.disim.mwt.letsjamrestapi.services.SongApiService;
import it.univaq.disim.mwt.letsjamrestapi.services.impl.SongApiServiceImpl;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class SongApiServiceFactory {
    private final static SongApiService service = new SongApiServiceImpl();

    public static SongApiService getSongApi() {
        return service;
    }
}
