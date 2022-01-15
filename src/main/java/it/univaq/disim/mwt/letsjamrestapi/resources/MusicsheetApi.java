package it.univaq.disim.mwt.letsjamrestapi.resources;

import java.math.BigDecimal;

import javax.annotation.Generated;
import javax.servlet.ServletConfig;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.factories.MusicsheetApiServiceFactory;
import it.univaq.disim.mwt.letsjamrestapi.models.Comment;
import it.univaq.disim.mwt.letsjamrestapi.models.MusicSheet;
import it.univaq.disim.mwt.letsjamrestapi.models.MusicSheetData;
import it.univaq.disim.mwt.letsjamrestapi.models.MusicsheetIdCommentBody;
import it.univaq.disim.mwt.letsjamrestapi.models.UpdateMusicsheetBody;
import it.univaq.disim.mwt.letsjamrestapi.models.NewMusicSheet;
import it.univaq.disim.mwt.letsjamrestapi.services.MusicsheetApiService;

@Path("/musicsheet")
@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class MusicsheetApi {
    private final MusicsheetApiService delegate;    
    public MusicsheetApi(@Context ServletConfig servletContext) {
        MusicsheetApiService delegate = null;
        if (servletContext != null) {
            String implClass = servletContext.getInitParameter("MusicsheetApi.implementation");
            if (implClass != null && !"".equals(implClass.trim())) {
                try {
                    delegate = (MusicsheetApiService) Class.forName(implClass).getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (delegate == null) {
            delegate = MusicsheetApiServiceFactory.getMusicsheetApi();
        }

        this.delegate = delegate;
    }

    @POST
    @Path("/{musicsheetId}/comment")
    @Consumes({ "application/json" })
    @Produces({ "application/json", "text/plain" })
    @Operation(summary = "Adds a new comment to specified musicSheet", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "musicsheet", "comment" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Comment.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response addComment(
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("musicsheetId") BigDecimal musicsheetId,
            @Parameter(in = ParameterIn.DEFAULT, description = "") MusicsheetIdCommentBody body,
            @Parameter(in = ParameterIn.QUERY, description = "Id of the parent comment. Required if the new comment is a reply to another comment") @QueryParam("parent") BigDecimal parent,
            @Context SecurityContext securityContext)
            throws NotFoundException {
        return delegate.addComment(musicsheetId, body, parent, securityContext);
    }

    @POST
    @Path("/{musicsheetId}/like/{userId}")
    @Produces({ "text/plain" })
    @Operation(summary = "Add like from specified user to specified musicsheet", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "musicsheet", "user" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Invalid Id supplied"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response addLike(
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("musicsheetId") BigDecimal musicsheetId,
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("userId") BigDecimal userId,
            @Context SecurityContext securityContext)
            throws NotFoundException {
        return delegate.addLike(musicsheetId, userId, securityContext);
    }

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json", "text/plain" })
    @Operation(summary = "Adds a new musicSheet", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "musicsheet" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MusicSheet.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "409", description = "MusicSheet already exists"),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response addMusicSheet(
            @Parameter(in = ParameterIn.DEFAULT, description = "", required = true) NewMusicSheet body,
            @Context SecurityContext securityContext)
            throws NotFoundException {
        return delegate.addMusicSheet(body, securityContext);
    }

    @DELETE
    @Path("/{musicsheetId}")
    @Produces({ "text/plain" })
    @Operation(summary = "Deletes musicsheet by id", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "musicsheet" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response deleteMusicSheetById(
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("musicsheetId") BigDecimal musicsheetId,
            @Context SecurityContext securityContext)
            throws NotFoundException {
        return delegate.deleteMusicSheetById(musicsheetId, securityContext);
    }

    @GET
    @Path("/{musicsheetId}")
    @Produces({ "application/json", "text/plain" })
    @Operation(summary = "Gets musicsheet by id", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "musicsheet" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MusicSheet.class))),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response getMusicSheetById(
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("musicsheetId") BigDecimal musicsheetId,
            @Context SecurityContext securityContext)
            throws NotFoundException {
        return delegate.getMusicSheetById(musicsheetId, securityContext);
    }

    @GET
    @Path("/{musicsheetId}/comments")
    @Produces({ "application/json", "text/plain" })
    @Operation(summary = "Gets all the comments for the specified musicsheet", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "musicsheet", "comment" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Comment.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response getMusicSheetComments(
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("musicsheetId") BigDecimal musicsheetId,
            @Context SecurityContext securityContext)
            throws NotFoundException {
        return delegate.getMusicSheetComments(musicsheetId, securityContext);
    }

    @GET
    @Path("/{musicsheetId}/data")
    @Produces({ "application/json", "text/plain" })
    @Operation(summary = "Gets the specified musicsheet data", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "musicsheet" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MusicSheetData.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response getMusicSheetData(
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("musicsheetId") BigDecimal musicsheetId,
            @Context SecurityContext securityContext)
            throws NotFoundException {
        return delegate.getMusicSheetData(musicsheetId, securityContext);
    }

    @DELETE
    @Path("/{musicsheetId}/like/{userId}")
    @Produces({ "text/plain" })
    @Operation(summary = "Removes like from specified user to specified musicsheet", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "musicsheet", "user" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Invalid Id supplied"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response removeLike(
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("musicsheetId") BigDecimal musicsheetId,
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("userId") BigDecimal userId,
            @Context SecurityContext securityContext)
            throws NotFoundException {
        return delegate.removeLike(musicsheetId, userId, securityContext);
    }

    @PUT
    @Path("/{musicsheetId}")
    @Consumes({ "application/json" })
    @Produces({ "text/plain" })
    @Operation(summary = "Updates a musicsheet by id", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "musicsheet" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Invalid Id supplied"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response updateMusicSheet(
            @Parameter(in = ParameterIn.DEFAULT, description = "", required = true) UpdateMusicsheetBody body,
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("musicsheetId") BigDecimal musicsheetId,
            @Context SecurityContext securityContext)
            throws NotFoundException {
        return delegate.updateMusicSheet(body, musicsheetId, securityContext);
    }
}
