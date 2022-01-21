package it.univaq.disim.mwt.letsjamrestapi.services.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Generated;
import javax.validation.constraints.DecimalMin;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.univaq.disim.mwt.letsjamrestapi.business.services.CommentDBService;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.models.Comment;
import it.univaq.disim.mwt.letsjamrestapi.models.CommentBody;
import it.univaq.disim.mwt.letsjamrestapi.services.CommentApiService;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class CommentApiServiceImpl extends CommentApiService {
    @Override
    public Response getCommentById(@DecimalMin("1") BigDecimal commentId, SecurityContext securityContext)
            throws ApiException {
        Comment commento;
        try {
            commento = CommentDBService.getCommentById(commentId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApiException(500);
        }
        return Response.ok().entity(commento).build();
    }

    @Override
    public Response getReplies(@DecimalMin("1") BigDecimal commentId, SecurityContext securityContext)
            throws ApiException {
        List<Comment> commenti;
        try {
            commenti = CommentDBService.getReplies(commentId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApiException(500);
        }
        return Response.ok().entity(commenti).build();
    }

    @Override
    public Response updateComment(@DecimalMin("1") BigDecimal commentId, CommentBody body,
            SecurityContext securityContext) throws ApiException {
        Comment commento;
        try {
            commento = CommentDBService.updateComment(commentId, body.getContent());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApiException(500);
        }
        return Response.ok().entity(commento).build();
    }
}
