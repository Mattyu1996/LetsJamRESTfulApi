package it.univaq.disim.mwt.letsjamrestapi.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.univaq.disim.mwt.letsjamrestapi.factories.UserApiServiceFactory;
import it.univaq.disim.mwt.letsjamrestapi.models.Genre;
import it.univaq.disim.mwt.letsjamrestapi.models.Instrument;
import it.univaq.disim.mwt.letsjamrestapi.models.User;
import it.univaq.disim.mwt.letsjamrestapi.models.UserIdGenresBody;
import it.univaq.disim.mwt.letsjamrestapi.models.UserIdInstrumentsBody;
import it.univaq.disim.mwt.letsjamrestapi.security.AuthLevel1;
import it.univaq.disim.mwt.letsjamrestapi.models.UpdateUserBody;
import it.univaq.disim.mwt.letsjamrestapi.services.UserApiService;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;

import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import javax.annotation.Generated;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.*;

@Path("/user")
@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class UserApi {
    private final UserApiService delegate;

    public UserApi(@Context ServletConfig servletContext) {
        UserApiService delegate = null;

        if (servletContext != null) {
            String implClass = servletContext.getInitParameter("UserApi.implementation");
            if (implClass != null && !"".equals(implClass.trim())) {
                try {
                    delegate = (UserApiService) Class.forName(implClass).getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (delegate == null) {
            delegate = UserApiServiceFactory.getUserApi();
        }
        
        this.delegate = delegate;
    }

    @POST
    @AuthLevel1
    @Path("/{userId}/genres")
    @Produces({ "text/plain" })
    @Operation(summary = "Adds specified genre from specified user's preferred genres", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "user", "genre" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Invalid Id supplied"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response addPreferredGenre(
            @Parameter(in = ParameterIn.PATH, description = "user id", required = true) @PathParam("userId") BigDecimal userId,
            @Parameter(in = ParameterIn.DEFAULT, description = "Instrument id to add to user's preferred genre" ) UserIdGenresBody body,
            @Context SecurityContext securityContext, @Context UriInfo uriInfo) throws NotFoundException, SQLException {
        return delegate.addPreferredGenre(userId, body.getGenreId(), securityContext);
    }


    @GET
    @AuthLevel1
    @Path("/{userId}/instruments")
    @Produces({ "application/json", "text/plain" })
    @Operation(summary = "Gets user's preferred instruments", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "user", "instrument" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Instrument.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid Id supplied"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response getUserPreferredInstruments(
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("userId") BigDecimal userId,
            @Context SecurityContext securityContext, @Context UriInfo uriInfo)
            throws NotFoundException, SQLException {
                System.out.println(uriInfo.getPath());
        return delegate.getUserPreferredInstruments(userId, securityContext);
    }

    @POST
    @AuthLevel1
    @Path("/{userId}/instruments")
    @Produces({ "text/plain" })
    @Operation(summary = "Adds specified instrument from specified user's preferred instruments", description = "", tags = {
            "user", "instrument" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Invalid Id supplied"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response addPreferredInstrument(
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("userId") BigDecimal userId,
            @Parameter(in = ParameterIn.DEFAULT, description = "Instrument id to add to user's preferred genre" ) UserIdInstrumentsBody body,
            @Context SecurityContext securityContext, @Context UriInfo uriInfo)
            throws NotFoundException, SQLException {
                    System.out.println(uriInfo.getPath());
        return delegate.addPreferredInstrument(userId, body.getInstrumentId(), securityContext);
    }

    @DELETE
    @AuthLevel1
    @Path("/{userId}/instruments/{instrumentId}")
    @Produces({ "text/plain" })
    @Operation(summary = "Removes specified instrument from specified user's preferred instruments", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "user", "instrument" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Invalid Id supplied"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))) })
    public Response removePreferredInstrument(
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("userId") BigDecimal userId,
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("instrumentId") BigDecimal instrumentId,
            @Context SecurityContext securityContext, @Context UriInfo uriInfo)
            throws NotFoundException, SQLException {
                System.out.println(uriInfo.getPath());
        return delegate.removePreferredInstrument(userId, instrumentId, securityContext);
    }

    @DELETE
    @AuthLevel1
    @Path("/{userId}")
    @Produces({ "text/plain" })
    @Operation(summary = "Deletes user by id", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "user" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response deleteUserById(
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("userId") BigDecimal userId,
            @Context SecurityContext securityContext, @Context UriInfo uriInfo)
            throws NotFoundException, SQLException {
                System.out.println(uriInfo.getPath());
        return delegate.deleteUserById(userId, securityContext);
    }
    
    @GET
    @AuthLevel1
    @Path("/{userId}")
    @Produces({ "application/json", "text/plain" })
    @Operation(summary = "Gets user by id", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "user" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response getUserById(
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("userId") BigDecimal userId,
            @Context SecurityContext securityContext, @Context UriInfo uriInfo)
            throws NotFoundException, SQLException {
                System.out.println(uriInfo.getPath());
        return delegate.getUserById(userId, securityContext);
    }

    @GET
    @AuthLevel1
    @Path("/{userId}/genres")
    @Produces({ "application/json", "text/plain" })
    @Operation(summary = "Gets user's preferred genres", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "user", "genre" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Genre.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid Id supplied"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response getUserPreferredGenres(
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("userId") BigDecimal userId,
            @Context SecurityContext securityContext, @Context UriInfo uriInfo)
            throws NotFoundException, SQLException {
                System.out.println(uriInfo.getPath());
        return delegate.getUserPreferredGenres(userId, securityContext);
    }

    @DELETE
    @AuthLevel1
    @Path("/{userId}/genres/{genreId}")
    @Produces({ "text/plain" })
    @Operation(summary = "Removes specified genre from specified user's preferred genres", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "user", "genre" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Invalid Id supplied"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response removePreferredGenre(
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("userId") BigDecimal userId,
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("genreId") BigDecimal genreId,
            @Context SecurityContext securityContext, @Context UriInfo uriInfo)
            throws NotFoundException, SQLException {
                System.out.println(uriInfo.getPath());
        return delegate.removePreferredGenre(userId, genreId, securityContext);
    }

    

    @PUT
    @AuthLevel1
    @Path("/avatar")
    @Consumes({ "image/jpeg" })
    @Produces({ "application/json", "text/plain" })
    @Operation(summary = "Updates specified user's avatar", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "user" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "400", description = "Invalid Id supplied"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response updateUserAvatar(
            @Parameter(in = ParameterIn.DEFAULT, description = "") InputStream stream,
            @Context SecurityContext securityContext, @Context UriInfo uriInfo,  @Context HttpServletRequest req)
            throws SQLException, ApiException {
                System.out.println(uriInfo.getPath());
        return delegate.updateUserAvatar(stream, securityContext, req);
    }

    @PUT
    @AuthLevel1
    @Path("/{userId}")
    @Consumes({ "application/json" })
    @Produces({ "application/json", "text/plain" })
    @Operation(summary = "Updates an existing user", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "user" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "404", description = "Element not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response updateUserById(
            @Parameter(in = ParameterIn.DEFAULT, description = "", required = true) UpdateUserBody body,
            @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("userId") BigDecimal userId,
            @Context SecurityContext securityContext, @Context UriInfo uriInfo)
            throws NotFoundException, SQLException {
                System.out.println(uriInfo.getPath());
        return delegate.updateUserById(body, userId, securityContext);
    }
}
