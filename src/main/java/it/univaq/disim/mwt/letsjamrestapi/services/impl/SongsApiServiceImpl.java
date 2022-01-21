package it.univaq.disim.mwt.letsjamrestapi.services.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Generated;
import javax.validation.constraints.DecimalMin;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.univaq.disim.mwt.letsjamrestapi.business.services.SongDBService;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.models.Song;
import it.univaq.disim.mwt.letsjamrestapi.models.Song.SongAlbumType;
import it.univaq.disim.mwt.letsjamrestapi.models.Song.SongSortEnum;
import it.univaq.disim.mwt.letsjamrestapi.services.SongsApiService;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class SongsApiServiceImpl extends SongsApiService {
    @Override
    public Response getSongs(
            String search, String sortby,
            String sortdirection, List<String> genres,
            Boolean explicit, Boolean hasLyrics,
            String albumtype, @DecimalMin("0") BigDecimal pagenumber,
            @DecimalMin("1") BigDecimal pagesize,
            SecurityContext securityContext) throws ApiException {
        String sortBy = null;
        System.out.println(sortby);
        if (sortby != null) {
            switch (sortby) {
                case "TITLE":
                    sortBy = SongSortEnum.TITLE.toString();
                    break;
                case "ALBUMNAME":
                    sortBy = SongSortEnum.ALBUMNAME.toString();
                    break;
                case "CREATEDATETIME":
                    sortBy = SongSortEnum.CREATEDATETIME.toString();
                    break;
                case "DURATION":
                    sortBy = SongSortEnum.DURATION.toString();
                    break;
                default:
                    break;
            }
        }

        String albumType = null;
        if (albumtype != null) {
            switch (albumtype) {
                case "SINGLE":
                    albumType = SongAlbumType.SINGLE.toString();
                    break;
                case "ALBUM":
                    albumType = SongAlbumType.ALBUM.toString();
                    break;
                case "COMPILATION":
                    albumType = SongAlbumType.COMPILATION.toString();
                    break;
                default:
                    break;
            }
        }

        List<Song> brani;
        try {
            brani = SongDBService.searchSongs(search, sortBy, sortdirection, genres, explicit, hasLyrics, albumType,
                    pagenumber, pagesize);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApiException(500);
        }
        return Response.ok().entity(brani).build();
    }
}
