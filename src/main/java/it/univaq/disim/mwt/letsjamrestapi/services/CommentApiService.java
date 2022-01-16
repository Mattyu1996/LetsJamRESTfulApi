package it.univaq.disim.mwt.letsjamrestapi.services;

import java.math.BigDecimal;

import javax.annotation.Generated;
import javax.validation.constraints.DecimalMin;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.CommentBody;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public abstract class CommentApiService {
    public abstract Response getCommentById(@DecimalMin("1") BigDecimal commentId, SecurityContext securityContext)
            throws NotFoundException;

    public abstract Response getReplies(@DecimalMin("1") BigDecimal commentId, SecurityContext securityContext)
            throws NotFoundException;

    public abstract Response updateComment(@DecimalMin("1") BigDecimal commentId, CommentBody body,
            SecurityContext securityContext) throws NotFoundException;
}
