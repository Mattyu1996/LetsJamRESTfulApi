package it.univaq.disim.mwt.letsjamrestapi.resources;

import java.sql.SQLException;

import javax.annotation.Generated;
import javax.servlet.ServletConfig;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.factories.AuthApiServiceFactory;
import it.univaq.disim.mwt.letsjamrestapi.models.AuthLoginBody;
import it.univaq.disim.mwt.letsjamrestapi.models.NewUser;
import it.univaq.disim.mwt.letsjamrestapi.models.User;
import it.univaq.disim.mwt.letsjamrestapi.security.AuthLevel1;
import it.univaq.disim.mwt.letsjamrestapi.services.AuthApiService;

@Path("/auth")
@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class AuthApi {
    private final AuthApiService delegate;

    public AuthApi(@Context ServletConfig servletContext) {
        AuthApiService delegate = null;

        if (servletContext != null) {
            String implClass = servletContext.getInitParameter("AuthApi.implementation");
            if (implClass != null && !"".equals(implClass.trim())) {
                try {
                    delegate = (AuthApiService) Class.forName(implClass).getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (delegate == null) {
            delegate = AuthApiServiceFactory.getAuthApi();
        }

        this.delegate = delegate;
    }

    @POST
    @Path("/register")
    @Consumes({ "application/json" })
    @Produces({ "application/json", "text/plain" })
    @Operation(summary = "Adds a new user", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "auth", "user" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "409", description = "User already exists"),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response addUser(
            @Parameter(in = ParameterIn.DEFAULT, description = "") NewUser body,
            @Context SecurityContext securityContext,
            @Context UriInfo uriInfo)
            throws SQLException, ApiException {
        System.out.println(uriInfo.getPath());
        return delegate.addUser(body, securityContext, uriInfo);
    }

    @POST
    @Path("/login")
    @Consumes({ "application/json" })
    @Produces({ "text/plain" })
    @Operation(summary = "Authentication for the api", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "auth" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful login", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response login(@Parameter(in = ParameterIn.DEFAULT, description = "") AuthLoginBody body,
            @Context SecurityContext securityContext, @Context UriInfo uriInfo)
            throws ApiException {
        System.out.println(uriInfo.getPath());
        return delegate.login(body, securityContext, uriInfo);
    }

    @DELETE
    @Path("/logout")
    @AuthLevel1
    @Produces({ "text/plain" })
    @Operation(summary = "Logs out the user", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "auth" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful logout"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response logout(@Context SecurityContext securityContext, @Context ContainerRequestContext req)
            throws SQLException, ApiException {
        System.out.println(req.getUriInfo().getPath());
        return delegate.logout(securityContext, req);
    }

    @GET
    @Path("/refresh")
    @AuthLevel1
    @Produces({ "text/plain" })
    @Operation(summary = "Refreshes the jwt token without relog in again", description = "", security = {
            @SecurityRequirement(name = "bearerAuth") }, tags = { "auth" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful logout"),
            @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
            @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    public Response refreshToken(@Context SecurityContext securityContext, @Context ContainerRequestContext req)
            throws ApiException {
        System.out.println(req.getUriInfo().getPath());
        return delegate.refreshToken(securityContext, req);
    }
}
