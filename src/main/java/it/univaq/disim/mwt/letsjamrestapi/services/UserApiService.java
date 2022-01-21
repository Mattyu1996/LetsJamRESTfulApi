package it.univaq.disim.mwt.letsjamrestapi.services;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;

import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.UpdateUserBody;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.annotation.Generated;
import javax.validation.constraints.*;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public abstract class UserApiService {
    public abstract Response addPreferredGenre(@DecimalMin("1") BigDecimal userId, @DecimalMin("1") BigDecimal genreId,
            SecurityContext securityContext) throws NotFoundException, SQLException;

    public abstract Response addPreferredInstrument(@DecimalMin("1") BigDecimal userId,
            @DecimalMin("1") BigDecimal instrumentId, SecurityContext securityContext) throws NotFoundException, SQLException;

    public abstract Response deleteUserById(@DecimalMin("1") BigDecimal userId, SecurityContext securityContext)
            throws NotFoundException, SQLException;

    public abstract Response getUserById(@DecimalMin("1") BigDecimal userId, SecurityContext securityContext)
            throws NotFoundException, SQLException;

    public abstract Response getUserPreferredGenres(@DecimalMin("1") BigDecimal userId, SecurityContext securityContext)
            throws NotFoundException, SQLException;

    public abstract Response getUserPreferredInstruments(@DecimalMin("1") BigDecimal userId,
            SecurityContext securityContext) throws NotFoundException, SQLException;

    public abstract Response removePreferredGenre(@DecimalMin("1") BigDecimal userId,
            @DecimalMin("1") BigDecimal genreId, SecurityContext securityContext) throws NotFoundException, SQLException;

    public abstract Response removePreferredInstrument(@DecimalMin("1") BigDecimal userId,
            @DecimalMin("1") BigDecimal instrumentId, SecurityContext securityContext) throws NotFoundException, SQLException;

    public abstract Response updateUserAvatar(@DecimalMin("1") BigDecimal userId, InputStream stream,
            SecurityContext securityContext) throws NotFoundException, SQLException;

    public abstract Response updateUserById(UpdateUserBody body, @DecimalMin("1") BigDecimal userId,
            SecurityContext securityContext) throws NotFoundException, SQLException;
}
