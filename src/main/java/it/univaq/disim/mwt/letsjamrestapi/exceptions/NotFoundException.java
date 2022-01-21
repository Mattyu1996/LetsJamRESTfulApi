package it.univaq.disim.mwt.letsjamrestapi.exceptions;

import javax.annotation.Generated;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class NotFoundException extends ApiException {
    private int code;
    public NotFoundException (String msg) {
        super(404, msg);
        this.code = 404;
    }
}
