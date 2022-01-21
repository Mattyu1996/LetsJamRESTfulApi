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
import it.univaq.disim.mwt.letsjamrestapi.factories.SongsApiServiceFactory;
import it.univaq.disim.mwt.letsjamrestapi.models.Song;
import it.univaq.disim.mwt.letsjamrestapi.security.AuthLevel1;
import it.univaq.disim.mwt.letsjamrestapi.services.SongsApiService;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Generated;
import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;


@Path("/songs")
@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class SongsApi {
   private final SongsApiService delegate;

   public SongsApi(@Context ServletConfig servletContext) {
      SongsApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("SongsApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (SongsApiService) Class.forName(implClass).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         }
      }

      if (delegate == null) {
         delegate = SongsApiServiceFactory.getSongsApi();
      }

      this.delegate = delegate;
   }

   @GET
   @AuthLevel1
   @Produces({ "application/json", "text/plain" })
   @Operation(summary = "Gets all songs", description = "", security = {
         @SecurityRequirement(name = "bearerAuth") }, tags = { "song" })
   @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Song.class)))),
         @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
         @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
   })
   public Response getSongs(
         @Parameter(in = ParameterIn.QUERY, description = "") @QueryParam("search") String search,
         @Parameter(in = ParameterIn.QUERY, description = "Name of the column for sorting musicsheets", schema = @Schema(allowableValues = {
               "TITLE", "ALBUMNAME", "DURATION", "CREATEDATETIME" })) @QueryParam("sortby") String sortby,
         @Parameter(in = ParameterIn.QUERY, description = "Direction of sorting. ASC or DESC", schema = @Schema(allowableValues = {
               "ASC", "DESC" })) @QueryParam("sortdirection") String sortdirection,
         @Parameter(in = ParameterIn.QUERY, description = "List of genres to search song for") @QueryParam("genres") List<String> genres,
         @Parameter(in = ParameterIn.QUERY, description = "Wheater to show songs with explicit content or not") @QueryParam("explicit") Boolean explicit,
         @Parameter(in = ParameterIn.QUERY, description = "Wheater to show only songs with lyrics or not") @QueryParam("hasLyrics") Boolean hasLyrics,
         @Parameter(in = ParameterIn.QUERY, description = "Wheater to show only songs with the specified albumType", schema = @Schema(allowableValues = {
               "ALBUM", "SINGLE", "COLLECTION" })) @QueryParam("albumtype") String albumtype,
         @Parameter(in = ParameterIn.QUERY, description = "The number of the page to skip before collect songs") @QueryParam("pagenumber") BigDecimal pagenumber,
         @Parameter(in = ParameterIn.QUERY, description = "The number elements to return") @QueryParam("pagesize") BigDecimal pagesize,
         @Context SecurityContext securityContext) throws NotFoundException, ApiException {
      return delegate.getSongs(search, sortby, sortdirection, genres, explicit, hasLyrics, albumtype, pagenumber,
            pagesize, securityContext);
   }
}
