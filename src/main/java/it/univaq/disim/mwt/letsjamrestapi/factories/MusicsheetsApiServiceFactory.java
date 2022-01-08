package it.univaq.disim.mwt.letsjamrestapi.factories;

import javax.annotation.Generated;
import it.univaq.disim.mwt.letsjamrestapi.services.MusicsheetsApiService;
import it.univaq.disim.mwt.letsjamrestapi.services.impl.MusicsheetsApiServiceImpl;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")public class MusicsheetsApiServiceFactory {
    private final static MusicsheetsApiService service = new MusicsheetsApiServiceImpl();

    public static MusicsheetsApiService getMusicsheetsApi() {
        return service;
    }
}
