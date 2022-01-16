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
import java.math.BigDecimal;
import java.util.List;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.factories.MusicsheetsApiServiceFactory;
import it.univaq.disim.mwt.letsjamrestapi.models.MusicSheet;
import it.univaq.disim.mwt.letsjamrestapi.security.AuthLevel1;
import it.univaq.disim.mwt.letsjamrestapi.services.MusicsheetsApiService;
import javax.annotation.Generated;
import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/musicsheets")
@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class MusicsheetsApi {
   private final MusicsheetsApiService delegate;

   public MusicsheetsApi(@Context ServletConfig servletContext) {
      MusicsheetsApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("MusicsheetsApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (MusicsheetsApiService) Class.forName(implClass).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         }
      }

      if (delegate == null) {
         delegate = MusicsheetsApiServiceFactory.getMusicsheetsApi();
      }

      this.delegate = delegate;
   }

   @GET
   @AuthLevel1
   @Produces({ "application/json", "text/plain" })
   @Operation(summary = "Gets all musicsheets", description = "", security = {
         @SecurityRequirement(name = "bearerAuth") }, tags = { "musicsheet" })
   @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MusicSheet.class)))),
         @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
         @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
   })
   public Response getAllMusicSheets(
         @Parameter(in = ParameterIn.QUERY, description = "") @QueryParam("search") String search,
         @Parameter(in = ParameterIn.QUERY, description = "Name of the column for sorting musicsheets", schema = @Schema(allowableValues = {
               "TITLE", "SONGTITLE", "CREATEDATETIME", "LIKES" })) @QueryParam("sortby") String sortby,
         @Parameter(in = ParameterIn.QUERY, description = "Direction of sorting. ASC or DESC", schema = @Schema(allowableValues = {
               "ASC", "DESC" })) @QueryParam("sortdirection") String sortdirection,
         @Parameter(in = ParameterIn.QUERY, description = "List of genres to search musicsheet for") @QueryParam("genres") List<String> genres,
         @Parameter(in = ParameterIn.QUERY, description = "List of instruments to search musicsheet for") @QueryParam("instruments") List<String> instruments,
         @Parameter(in = ParameterIn.QUERY, description = "Wheater to show only vierified musicsheets or not") @QueryParam("verified") Boolean verified,
         @Parameter(in = ParameterIn.QUERY, description = "Wheater to show only rearranged musicsheets or not") @QueryParam("rearranged") Boolean rearranged,
         @Parameter(in = ParameterIn.QUERY, description = "Wheater to show only musicsheets that contain tablature or not") @QueryParam("tablature") Boolean tablature,
         @Parameter(in = ParameterIn.QUERY, description = "The number of the page to skip before collect musicsheets") @QueryParam("pagenumber") BigDecimal pagenumber,
         @Parameter(in = ParameterIn.QUERY, description = "The number elements to return") @QueryParam("pagesize") BigDecimal pagesize,
         @Context SecurityContext securityContext)
         throws NotFoundException {
      return delegate.getAllMusicSheets(search, sortby, sortdirection, genres, instruments, verified, rearranged,
            tablature, pagenumber, pagesize, securityContext);
   }
}
