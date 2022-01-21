package it.univaq.disim.mwt.letsjamrestapi.services;

import java.math.BigDecimal;

import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.annotation.Generated;
import javax.validation.constraints.*;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public abstract class SongApiService {
    public abstract Response getSongById(@DecimalMin("1") BigDecimal songId, SecurityContext securityContext)
            throws NotFoundException, ApiException;
}
