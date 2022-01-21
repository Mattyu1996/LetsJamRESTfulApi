package it.univaq.disim.mwt.letsjamrestapi.resources;

import javax.annotation.Generated;
import javax.servlet.ServletConfig;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.factories.InstrumentsApiServiceFactory;
import it.univaq.disim.mwt.letsjamrestapi.models.Instrument;
import it.univaq.disim.mwt.letsjamrestapi.security.AuthLevel1;
import it.univaq.disim.mwt.letsjamrestapi.services.InstrumentsApiService;

@Path("/instruments")
@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class InstrumentsApi {
   private final InstrumentsApiService delegate;

   public InstrumentsApi(@Context ServletConfig servletContext) {
      InstrumentsApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("InstrumentsApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (InstrumentsApiService) Class.forName(implClass).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         }
      }

      if (delegate == null) {
         delegate = InstrumentsApiServiceFactory.getInstrumentsApi();
      }

      this.delegate = delegate;
   }

   @GET
   @AuthLevel1
   @Produces({ "application/json", "text/plain" })
   @Operation(summary = "gets all the instruments", description = "", security = {
         @SecurityRequirement(name = "bearerAuth") }, tags = { "instrument" })
   @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Scuccessful Operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Instrument.class)))),
         @ApiResponse(responseCode = "401", description = "bearer token missing or invalid"),
         @ApiResponse(responseCode = "500", description = "General errror occurred", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
   })
   public Response getAllInstruments(@Context SecurityContext securityContext)
         throws ApiException {
      return delegate.getAllInstruments(securityContext);
   }
}
