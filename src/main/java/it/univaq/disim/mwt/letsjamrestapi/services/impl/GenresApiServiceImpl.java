package it.univaq.disim.mwt.letsjamrestapi.services.impl;

import it.univaq.disim.mwt.letsjamrestapi.business.services.GenreDBService;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.Genre;
import it.univaq.disim.mwt.letsjamrestapi.services.GenresApiService;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Generated;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class GenresApiServiceImpl extends GenresApiService {
    @Override
    public Response getAllGenres(SecurityContext securityContext) throws ApiException {
        List<Genre> generi;
        try {
            generi = GenreDBService.getAllGenres();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApiException(500);
        }
        return Response.ok().entity(generi).build();
    }
}
