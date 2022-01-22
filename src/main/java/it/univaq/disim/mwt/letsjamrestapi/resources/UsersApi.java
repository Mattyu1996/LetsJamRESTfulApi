package it.univaq.disim.mwt.letsjamrestapi.resources;

import javax.annotation.Generated;
import javax.servlet.ServletConfig;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
import it.univaq.disim.mwt.letsjamrestapi.factories.UsersApiServiceFactory;
import it.univaq.disim.mwt.letsjamrestapi.models.User;
import it.univaq.disim.mwt.letsjamrestapi.security.AuthLevel1;
import it.univaq.disim.mwt.letsjamrestapi.services.UsersApiService;

@Path("/users")
@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class UsersApi {
   private final UsersApiService delegate;

   public UsersApi(@Context ServletConfig servletContext) {
      UsersApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("UsersApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (UsersApiService) Class.forName(implClass).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         }
      }

      if (delegate == null) {
         delegate = UsersApiServiceFactory.getUsersApi();
      }

      this.delegate = delegate;
   }

   @GET
   @AuthLevel1
   @Produces({ "application/json", "text/plain" })
   @Operation(summary = "Gets all users", description = "", security = {
         @SecurityRequirement(name = "bearerAuth") }, tags = { "user" })
   @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))),
         @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
         @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
   })
   public Response getAllUsers(
         @Parameter(in = ParameterIn.QUERY, description = "Email of the user to get") @QueryParam("email") String email,
         @Parameter(in = ParameterIn.QUERY, description = "Username of the user to get") @QueryParam("username") String username,
         @Parameter(in = ParameterIn.QUERY, description = "Filter by user role", schema = @Schema(allowableValues = {
               "UTENTE", "AMMINISTRATORE" })) @QueryParam("role") String role,
         @Context SecurityContext securityContext, @Context UriInfo uriInfo)
         throws ApiException {
         System.out.println(uriInfo.getPath());
      return delegate.getAllUsers(email, username, role, securityContext);
   }
}
