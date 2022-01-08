package it.univaq.disim.mwt.letsjamrestapi.services.impl;

import java.util.List;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.ScoreAnalyzeBody;
import it.univaq.disim.mwt.letsjamrestapi.models.ScorePartsBody;
import it.univaq.disim.mwt.letsjamrestapi.services.ScoreApiService;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.annotation.Generated;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class ScoreApiServiceImpl extends ScoreApiService {
    @Override
    public Response analyzeScore(ScoreAnalyzeBody body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response getScoreWithOnlyParts(ScorePartsBody body, SecurityContext securityContext)
            throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response makeEmptyScore(List<String> instruments, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
