package it.univaq.disim.mwt.letsjamrestapi.resources;

import java.math.BigDecimal;

import javax.annotation.Generated;
import javax.servlet.ServletConfig;
import javax.ws.rs.GET;
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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.factories.SongApiServiceFactory;
import it.univaq.disim.mwt.letsjamrestapi.models.Song;
import it.univaq.disim.mwt.letsjamrestapi.security.AuthLevel1;
import it.univaq.disim.mwt.letsjamrestapi.services.SongApiService;

@Path("/song")
@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class SongApi {
   private final SongApiService delegate;

   public SongApi(@Context ServletConfig servletContext) {
      SongApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("SongApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (SongApiService) Class.forName(implClass).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         }
      }

      if (delegate == null) {
         delegate = SongApiServiceFactory.getSongApi();
      }

      this.delegate = delegate;
   }

   @GET
   @AuthLevel1
   @Path("/{songId}")
   @Produces({ "application/json", "text/plain" })
   @Operation(summary = "Gets a song by id", description = "", security = {
         @SecurityRequirement(name = "bearerAuth") }, tags = { "song" })
   @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Song.class))),
         @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
         @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
         @ApiResponse(responseCode = "404", description = "Element not found", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class))),
         @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
   })
   public Response getSongById(
         @Parameter(in = ParameterIn.PATH, description = "", required = true) @PathParam("songId") BigDecimal songId,
         @Context SecurityContext securityContext, @Context UriInfo uriInfo)
         throws ApiException {
      System.out.println(uriInfo.getPath());
      return delegate.getSongById(songId, securityContext);
   }
}
