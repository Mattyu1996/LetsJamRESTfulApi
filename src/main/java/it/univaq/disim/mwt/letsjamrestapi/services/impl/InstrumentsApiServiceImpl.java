package it.univaq.disim.mwt.letsjamrestapi.services.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Generated;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.univaq.disim.mwt.letsjamrestapi.business.services.InstrumentDBService;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.models.Instrument;
import it.univaq.disim.mwt.letsjamrestapi.services.InstrumentsApiService;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class InstrumentsApiServiceImpl extends InstrumentsApiService {
    @Override
    public Response getAllInstruments(SecurityContext securityContext) throws ApiException {
        List<Instrument> strumenti;
        try {
            strumenti = InstrumentDBService.getAllInstruments();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApiException(500);
        }
        return Response.ok().entity(strumenti).build();
    }
}
