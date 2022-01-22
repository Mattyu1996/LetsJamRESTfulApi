package it.univaq.disim.mwt.letsjamrestapi.resources;

import java.math.BigDecimal;

import javax.annotation.Generated;
import javax.servlet.ServletConfig;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.factories.CommentApiServiceFactory;
import it.univaq.disim.mwt.letsjamrestapi.models.Comment;
import it.univaq.disim.mwt.letsjamrestapi.models.CommentBody;
import it.univaq.disim.mwt.letsjamrestapi.security.AuthLevel1;
import it.univaq.disim.mwt.letsjamrestapi.services.CommentApiService;

@Path("/comment")
@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class CommentApi {
    private final CommentApiService delegate;

    public CommentApi(@Context ServletConfig servletContext) {
        CommentApiService delegate = null;

        if (servletContext != null) {
            String implClass = servletContext.getInitParameter("CommentApi.implementation");
            if (implClass != null && !"".equals(implClass.trim())) {
                try {
                    delegate = (CommentApiService) Class.forName(implClass).getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (delegate == null) {
            delegate = CommentApiServiceFactory.getCommentApi();
        }

        this.delegate = delegate;
    }

    @GET
    @AuthLevel1
    @Path("/{commentId}")
    @Produces({ "application/json", "text/plain" })
    @Operation(summary = "Gets a comment by id", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "comment" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response getCommentById(
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("commentId") BigDecimal commentId,
            @Context SecurityContext securityContext, @Context UriInfo uriInfo)
            throws ApiException {
            System.out.println(uriInfo.getPath());
        return delegate.getCommentById(commentId, securityContext);
    }

    @GET
    @AuthLevel1
    @Path("/{commentId}/replies")
    @Produces({ "application/json", "text/plain" })
    @Operation(summary = "Gets all the replies to the specified comment", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "comment" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Comment.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response getReplies(
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("commentId") BigDecimal commentId,
            @Context SecurityContext securityContext, @Context UriInfo uriInfo)
            throws ApiException {
            System.out.println(uriInfo.getPath());
        return delegate.getReplies(commentId, securityContext);
    }

    @PATCH
    @AuthLevel1
    @Path("/{commentId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json", "text/plain" })
    @Operation(summary = "Updates comment content", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "comment" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Comment.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))) })
    public Response updateComment(
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("commentId") BigDecimal commentId,
            @Parameter(in = ParameterIn.DEFAULT, description = "") CommentBody body,
            @Context SecurityContext securityContext,
            @Context UriInfo uriInfo)
            throws ApiException {
            System.out.println(uriInfo.getPath());
        return delegate.updateComment(commentId, body, securityContext);
    }
}
