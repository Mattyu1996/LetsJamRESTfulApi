package it.univaq.disim.mwt.letsjamrestapi.services.impl;

import it.univaq.disim.mwt.letsjamrestapi.business.services.GenreDBService;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.Genre;
import it.univaq.disim.mwt.letsjamrestapi.services.GenresApiService;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import java.util.List;

import javax.annotation.Generated;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class GenresApiServiceImpl extends GenresApiService {
    @Override
    public Response getAllGenres(SecurityContext securityContext) throws NotFoundException {
        List<Genre> generi = GenreDBService.getAllGenres();
        return Response.ok().entity(generi).build();
    }
}
