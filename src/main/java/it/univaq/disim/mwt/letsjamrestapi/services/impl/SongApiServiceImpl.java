package it.univaq.disim.mwt.letsjamrestapi.services.impl;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.annotation.Generated;
import javax.validation.constraints.DecimalMin;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.univaq.disim.mwt.letsjamrestapi.business.services.SongDBService;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.models.Song;
import it.univaq.disim.mwt.letsjamrestapi.services.SongApiService;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class SongApiServiceImpl extends SongApiService {
    @Override
    public Response getSongById(@DecimalMin("1") BigDecimal songId, SecurityContext securityContext)
            throws ApiException {
        Song brano;
        try {
            brano = SongDBService.getSongById(songId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApiException(500);
        }
        return Response.ok().entity(brano).build();
    }
}
