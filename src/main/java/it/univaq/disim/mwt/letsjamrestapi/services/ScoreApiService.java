package it.univaq.disim.mwt.letsjamrestapi.services;

import java.util.List;

import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.ScoreResponseBody;
import it.univaq.disim.mwt.letsjamrestapi.models.ScorePartsBody;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.annotation.Generated;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public abstract class ScoreApiService {
    public abstract Response analyzeScore(ScoreResponseBody body, SecurityContext securityContext)
            throws NotFoundException, ApiException;

    public abstract Response getScoreWithOnlyParts(ScorePartsBody body, SecurityContext securityContext)
            throws NotFoundException;

    public abstract Response makeEmptyScore(List<String> instruments, SecurityContext securityContext)
            throws NotFoundException;
}
