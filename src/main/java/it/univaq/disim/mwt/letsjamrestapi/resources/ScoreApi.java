package it.univaq.disim.mwt.letsjamrestapi.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.factories.ScoreApiServiceFactory;
import it.univaq.disim.mwt.letsjamrestapi.models.InlineResponse200;
import it.univaq.disim.mwt.letsjamrestapi.models.InlineResponse2001;
import it.univaq.disim.mwt.letsjamrestapi.models.ScoreResponseBody;
import it.univaq.disim.mwt.letsjamrestapi.models.ScorePartsBody;
import it.univaq.disim.mwt.letsjamrestapi.services.ScoreApiService;
import java.util.List;
import javax.annotation.Generated;
import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/score")
@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class ScoreApi {
    private final ScoreApiService delegate;

    public ScoreApi(@Context ServletConfig servletContext) {
        ScoreApiService delegate = null;

        if (servletContext != null) {
            String implClass = servletContext.getInitParameter("ScoreApi.implementation");
            if (implClass != null && !"".equals(implClass.trim())) {
                try {
                    delegate = (ScoreApiService) Class.forName(implClass).getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (delegate == null) {
            delegate = ScoreApiServiceFactory.getScoreApi();
        }

        this.delegate = delegate;
    }

    @POST
    @Path("/analyze")
    @Consumes({ "application/json" })
    @Produces({ "application/json", "text/plain" })
    @Operation(summary = "Analyzes the provided score and return the title and author written inside", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "score" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InlineResponse2001.class))),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response analyzeScore(
            @Parameter(in = ParameterIn.DEFAULT, description = "", required = true) ScoreResponseBody body,
            @Context SecurityContext securityContext)
            throws NotFoundException {
        return delegate.analyzeScore(body, securityContext);
    }

    @POST
    @Path("/parts")
    @Consumes({ "application/json" })
    @Produces({ "application/json", "text/plain" })
    @Operation(summary = "Extracts from the supplied score only parts in the specified part list", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "score" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ScorePartsBody.class))),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response getScoreWithOnlyParts(
            @Parameter(in = ParameterIn.DEFAULT, description = "", required = true) ScorePartsBody body,
            @Context SecurityContext securityContext)
            throws NotFoundException {
        return delegate.getScoreWithOnlyParts(body, securityContext);
    }

    @GET
    @Produces({ "application/json", "text/plain" })
    @Operation(summary = "Makes an empty score from the specified instrument list", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "score" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InlineResponse200.class))),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))) 
        })
    public Response makeEmptyScore(
            @Parameter(in = ParameterIn.QUERY, description = "instrument list used to make parts in the score") @QueryParam("instruments") List<String> instruments,
            @Context SecurityContext securityContext)
            throws NotFoundException {
        return delegate.makeEmptyScore(instruments, securityContext);
    }
}
