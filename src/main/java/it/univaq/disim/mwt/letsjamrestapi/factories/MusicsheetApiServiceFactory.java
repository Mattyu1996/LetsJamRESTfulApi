package it.univaq.disim.mwt.letsjamrestapi.factories;

import javax.annotation.Generated;
import it.univaq.disim.mwt.letsjamrestapi.services.MusicsheetApiService;
import it.univaq.disim.mwt.letsjamrestapi.services.impl.MusicsheetApiServiceImpl;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class MusicsheetApiServiceFactory {
    private final static MusicsheetApiService service = new MusicsheetApiServiceImpl();

    public static MusicsheetApiService getMusicsheetApi() {
        return service;
    }
}
