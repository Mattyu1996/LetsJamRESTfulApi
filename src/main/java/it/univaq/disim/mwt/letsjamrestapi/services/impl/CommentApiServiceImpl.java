package it.univaq.disim.mwt.letsjamrestapi.services.impl;

import java.math.BigDecimal;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.CommentCommentIdBody;
import it.univaq.disim.mwt.letsjamrestapi.services.CommentApiService;
import javax.annotation.Generated;
import javax.validation.constraints.*;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class CommentApiServiceImpl extends CommentApiService {
    @Override
    public Response getCommentById( @DecimalMin("1")BigDecimal commentId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response getReplies( @DecimalMin("1")BigDecimal commentId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response updateComment( @DecimalMin("1")BigDecimal commentId, CommentCommentIdBody body, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
