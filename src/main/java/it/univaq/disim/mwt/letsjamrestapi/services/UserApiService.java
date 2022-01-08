package it.univaq.disim.mwt.letsjamrestapi.services;

import java.math.BigDecimal;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.UserUserIdBody;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.annotation.Generated;
import javax.validation.constraints.*;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public abstract class UserApiService {
    public abstract Response addPreferredGenre(@DecimalMin("1") BigDecimal userId, @DecimalMin("1") BigDecimal genreId,
            SecurityContext securityContext) throws NotFoundException;

    public abstract Response addPreferredInstrument(@DecimalMin("1") BigDecimal userId,
            @DecimalMin("1") BigDecimal instrumentId, SecurityContext securityContext) throws NotFoundException;

    public abstract Response deleteUserById(@DecimalMin("1") BigDecimal userId, SecurityContext securityContext)
            throws NotFoundException;

    public abstract Response getUserByEmail(String email, SecurityContext securityContext) throws NotFoundException;

    public abstract Response getUserById(@DecimalMin("1") BigDecimal userId, SecurityContext securityContext)
            throws NotFoundException;

    public abstract Response getUserByUsername(String username, SecurityContext securityContext)
            throws NotFoundException;

    public abstract Response getUserPreferredGenres(@DecimalMin("1") BigDecimal userId, SecurityContext securityContext)
            throws NotFoundException;

    public abstract Response getUserPreferredInstruments(@DecimalMin("1") BigDecimal userId,
            SecurityContext securityContext) throws NotFoundException;

    public abstract Response removePreferredGenre(@DecimalMin("1") BigDecimal userId,
            @DecimalMin("1") BigDecimal genreId, SecurityContext securityContext) throws NotFoundException;

    public abstract Response removePreferredInstrument(@DecimalMin("1") BigDecimal userId,
            @DecimalMin("1") BigDecimal instrumentId, SecurityContext securityContext) throws NotFoundException;

    public abstract Response updateUserAvatar(@DecimalMin("1") BigDecimal userId, Object body,
            SecurityContext securityContext) throws NotFoundException;

    public abstract Response updateUserById(UserUserIdBody body, @DecimalMin("1") BigDecimal userId,
            SecurityContext securityContext) throws NotFoundException;
}
