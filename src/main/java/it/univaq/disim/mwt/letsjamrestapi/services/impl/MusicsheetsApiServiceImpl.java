package it.univaq.disim.mwt.letsjamrestapi.services.impl;

import java.math.BigDecimal;
import java.util.List;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.services.MusicsheetsApiService;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.annotation.Generated;
import javax.validation.constraints.*;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class MusicsheetsApiServiceImpl extends MusicsheetsApiService {
    @Override
    public Response getAllMusicSheets(
            String search, String sortby,
            String sortdirection, List<String> genres,
            List<String> instruments, Boolean verified, Boolean rearranged, Boolean tablature,
            @DecimalMin("0") BigDecimal pagenumber, @DecimalMin("1") BigDecimal pagesize,
            SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
