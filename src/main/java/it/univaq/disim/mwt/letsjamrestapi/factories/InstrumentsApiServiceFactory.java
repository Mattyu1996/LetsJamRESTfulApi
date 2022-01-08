package it.univaq.disim.mwt.letsjamrestapi.factories;

import javax.annotation.Generated;

import it.univaq.disim.mwt.letsjamrestapi.services.InstrumentsApiService;
import it.univaq.disim.mwt.letsjamrestapi.services.impl.InstrumentsApiServiceImpl;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class InstrumentsApiServiceFactory {
    private final static InstrumentsApiService service = new InstrumentsApiServiceImpl();

    public static InstrumentsApiService getInstrumentsApi() {
        return service;
    }
}
