package it.univaq.disim.mwt.letsjamrestapi.factories;

import javax.annotation.Generated;

import it.univaq.disim.mwt.letsjamrestapi.services.CommentApiService;
import it.univaq.disim.mwt.letsjamrestapi.services.impl.CommentApiServiceImpl;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class CommentApiServiceFactory {
    private final static CommentApiService service = new CommentApiServiceImpl();

    public static CommentApiService getCommentApi() {
        return service;
    }
}
