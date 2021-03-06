package it.univaq.disim.mwt.letsjamrestapi.services.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import it.univaq.disim.mwt.letsjamrestapi.business.services.MusicsheetDBService;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.models.MusicSheet;
import it.univaq.disim.mwt.letsjamrestapi.models.MusicSheet.MusicSheetSortEnum;
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
            SecurityContext securityContext) throws ApiException {
        String sortBy = null;
        if(sortby != null){
            switch (sortby) {
                case "TITLE":
                    sortBy = MusicSheetSortEnum.TITLE.toString();
                    break;
                case "SONGTITLE":
                    sortBy = MusicSheetSortEnum.SONGTITLE.toString();
                    break;
                case "CREATEDATETIME":
                    sortBy = MusicSheetSortEnum.CREATEDATETIME.toString();
                    break;
                case "LIKES":
                    sortBy = MusicSheetSortEnum.LIKES.toString();
                    break;        
                default:
                    break;
            }
        }
        List<MusicSheet> spartiti;
        try {
            spartiti = MusicsheetDBService.searchMusicSheets(search, sortBy, sortdirection, genres, instruments, verified, rearranged, tablature, pagenumber, pagesize);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApiException(500);
        }
        return Response.ok().entity(spartiti).build();
    }
}
