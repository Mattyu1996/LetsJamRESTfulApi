package it.univaq.disim.mwt.letsjamrestapi.services.impl;

import it.univaq.disim.mwt.letsjamrestapi.business.services.InstrumentDBService;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.Instrument;
import it.univaq.disim.mwt.letsjamrestapi.services.InstrumentsApiService;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import java.util.List;

import javax.annotation.Generated;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class InstrumentsApiServiceImpl extends InstrumentsApiService {
    @Override
    public Response getAllInstruments(SecurityContext securityContext) throws NotFoundException {
        List<Instrument> strumenti = InstrumentDBService.getAllInstruments();
        return Response.ok().entity(strumenti).build();
    }
}
