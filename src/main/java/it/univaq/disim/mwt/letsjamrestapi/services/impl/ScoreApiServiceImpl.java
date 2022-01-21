package it.univaq.disim.mwt.letsjamrestapi.services.impl;

import java.util.List;

import it.univaq.disim.mwt.letsjamrestapi.business.services.ScoreAnalyzerService;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.ScoreResponseBody;
import it.univaq.disim.mwt.letsjamrestapi.models.ScoreAnalyzeBody;
import it.univaq.disim.mwt.letsjamrestapi.models.ScorePartsBody;
import it.univaq.disim.mwt.letsjamrestapi.services.ScoreApiService;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.annotation.Generated;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class ScoreApiServiceImpl extends ScoreApiService {
    @Override
    public Response analyzeScore(ScoreResponseBody body, SecurityContext securityContext) throws ApiException {
        ScoreAnalyzerService as = new ScoreAnalyzerService();
        ScoreAnalyzeBody result = new ScoreAnalyzeBody();
        result.setTitle(as.getScoreTitle(body.getScore()));
        result.setAuthor(as.getScoreAuthor(body.getScore()));
        return Response.ok().entity(result).build();
    }

    @Override
    public Response getScoreWithOnlyParts(ScorePartsBody body, SecurityContext securityContext)
            throws NotFoundException {
        ScoreAnalyzerService as = new ScoreAnalyzerService();
        ScoreResponseBody result = new ScoreResponseBody();
        result.setScore(as.extractInstrumentPart(body.getScore(), body.getPartlist()));
        return Response.ok().entity(result).build();
    }

    @Override
    public Response makeEmptyScore(List<String> instruments, SecurityContext securityContext) throws NotFoundException {
        ScoreAnalyzerService as = new ScoreAnalyzerService();
        ScoreResponseBody result = new ScoreResponseBody();
        result.setScore(as.makeEmptyScore(instruments));
        return Response.ok().entity(result).build();
    }
}
