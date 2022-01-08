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
import it.univaq.disim.mwt.letsjamrestapi.factories.SongApiServiceFactory;
import it.univaq.disim.mwt.letsjamrestapi.models.Song;
import it.univaq.disim.mwt.letsjamrestapi.services.SongApiService;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;


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
         @Context SecurityContext securityContext)
         throws NotFoundException {
      return delegate.getSongById(songId, securityContext);
   }
}
