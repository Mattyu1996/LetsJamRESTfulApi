package it.univaq.disim.mwt.letsjamrestapi.services.impl;

import java.math.BigDecimal;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.UserUserIdBody;
import it.univaq.disim.mwt.letsjamrestapi.services.UserApiService;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.annotation.Generated;
import javax.validation.constraints.*;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class UserApiServiceImpl extends UserApiService {
    @Override
    public Response addPreferredGenre(@DecimalMin("1") BigDecimal userId, @DecimalMin("1") BigDecimal genreId,
            SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response addPreferredInstrument(@DecimalMin("1") BigDecimal userId, @DecimalMin("1") BigDecimal instrumentId,
            SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response deleteUserById(@DecimalMin("1") BigDecimal userId, SecurityContext securityContext)
            throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response getUserById(@DecimalMin("1") BigDecimal userId, SecurityContext securityContext)
            throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response getUserPreferredGenres(@DecimalMin("1") BigDecimal userId, SecurityContext securityContext)
            throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response getUserPreferredInstruments(@DecimalMin("1") BigDecimal userId, SecurityContext securityContext)
            throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response removePreferredGenre(@DecimalMin("1") BigDecimal userId, @DecimalMin("1") BigDecimal genreId,
            SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response removePreferredInstrument(@DecimalMin("1") BigDecimal userId,
            @DecimalMin("1") BigDecimal instrumentId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response updateUserAvatar(@DecimalMin("1") BigDecimal userId, Object body, SecurityContext securityContext)
            throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response updateUserById(UserUserIdBody body, @DecimalMin("1") BigDecimal userId,
            SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
