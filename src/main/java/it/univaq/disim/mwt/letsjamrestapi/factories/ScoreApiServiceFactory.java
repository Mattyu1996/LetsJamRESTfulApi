package it.univaq.disim.mwt.letsjamrestapi.factories;

import javax.annotation.Generated;
import it.univaq.disim.mwt.letsjamrestapi.services.ScoreApiService;
import it.univaq.disim.mwt.letsjamrestapi.services.impl.ScoreApiServiceImpl;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class ScoreApiServiceFactory {
    private final static ScoreApiService service = new ScoreApiServiceImpl();

    public static ScoreApiService getScoreApi() {
        return service;
    }
}
