package it.univaq.disim.mwt.letsjamrestapi.exceptions;

import javax.annotation.Generated;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class ApiException extends Exception{
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }

    public ApiException (int code) {
        super();
        this.code = code;
    }

    public int getCode(){
        return this.code;
    }
}
