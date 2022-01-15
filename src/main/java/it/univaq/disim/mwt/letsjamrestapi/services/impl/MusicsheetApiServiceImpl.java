package it.univaq.disim.mwt.letsjamrestapi.services.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.univaq.disim.mwt.letsjamrestapi.business.MongoDb;
import it.univaq.disim.mwt.letsjamrestapi.business.services.GenreDBService;
import it.univaq.disim.mwt.letsjamrestapi.business.services.MusicsheetDBService;
import it.univaq.disim.mwt.letsjamrestapi.business.services.ScoreAnalyzerService;
import it.univaq.disim.mwt.letsjamrestapi.business.services.SongDBService;
import it.univaq.disim.mwt.letsjamrestapi.business.services.UserDBService;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.Genre;
import it.univaq.disim.mwt.letsjamrestapi.models.Instrument;
import it.univaq.disim.mwt.letsjamrestapi.models.MusicSheet;
import it.univaq.disim.mwt.letsjamrestapi.models.MusicSheetData;
import it.univaq.disim.mwt.letsjamrestapi.models.MusicsheetIdCommentBody;
import it.univaq.disim.mwt.letsjamrestapi.models.MusicsheetMusicsheetIdBody;
import it.univaq.disim.mwt.letsjamrestapi.models.NewMusicSheet;
import it.univaq.disim.mwt.letsjamrestapi.models.Song;
import it.univaq.disim.mwt.letsjamrestapi.services.MusicsheetApiService;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.mongodb.BasicDBObject;

import org.bson.conversions.Bson;

import javax.annotation.Generated;
import javax.servlet.ServletContext;
import javax.validation.constraints.*;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class MusicsheetApiServiceImpl extends MusicsheetApiService {
    
    @Override
    public Response addComment(@DecimalMin("1") BigDecimal musicsheetId, MusicsheetIdCommentBody body,
            BigDecimal parent, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response addLike(@DecimalMin("1") BigDecimal musicsheetId, @DecimalMin("1") BigDecimal userId,
            SecurityContext securityContext) throws NotFoundException {
        UserDBService.addLike(userId, musicsheetId);
        return Response.ok().build();
    }

    @Override
    public Response addMusicSheet(NewMusicSheet body, SecurityContext securityContext) throws NotFoundException {
        
        ScoreAnalyzerService as = new ScoreAnalyzerService();
        Map<String, String> mappings = as.getInstruments(body.getContent());
		List<Instrument> strumenti = as.toInstrumentList(mappings);

        MusicSheetData data = new MusicSheetData();
        data.setContent(body.getContent());
        data.setInstrumentMapping(mappings);

        MusicSheet spartito = new MusicSheet();
        spartito.setInstruments(strumenti);
        spartito.setHasTablature(as.hasTablature(body.getContent()));
        spartito.setTitle(body.getTitle());
        spartito.setAuthor(body.getAuthor());
        spartito.setUser(UserDBService.getUserById(BigDecimal.valueOf((long) 4)).get(0));
        spartito.setRearranged(body.isRearranged());
        spartito.setVerified(false);
        spartito.setVisibility(body.isVisibility());

        Song s= new Song();
        if(body.getSong().getId() != null){
           s = SongDBService.getSongById(body.getSong().getId());
        }
        else if(body.getSong().getSpotifyId() != null){
            //get from spotify
        }
        else{
           s.setTitle(body.getSong().getTitle()); 
           s.setAuthor(body.getSong().getAuthor());
           SongDBService.addSong(s);
        }
        
        Genre g = GenreDBService.getGenreById(body.getSong().getGenreId());
        s.setGenre(g);
        spartito.setSong(s);

        int id = MusicsheetDBService.addMusicSheet(spartito);
        MusicsheetDBService.addMusicSheetData(data, BigDecimal.valueOf((long) id));

        return Response.ok().entity(MusicsheetDBService.getMusicsheetById(BigDecimal.valueOf((long) id))).build();
    }

    @Override
    public Response deleteMusicSheetById(@DecimalMin("1") BigDecimal musicsheetId, SecurityContext securityContext)
            throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response getMusicSheetById(@DecimalMin("1") BigDecimal musicsheetId, SecurityContext securityContext)
            throws NotFoundException {
        MusicSheet spartito = MusicsheetDBService.getMusicsheetById(musicsheetId);
        return Response.ok().entity(spartito).build();
    }

    @Override
    public Response getMusicSheetComments(@DecimalMin("1") BigDecimal musicsheetId, SecurityContext securityContext)
            throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response getMusicSheetData(@DecimalMin("1") BigDecimal musicsheetId, SecurityContext securityContext)
            throws NotFoundException {   
        MusicSheetData data = MusicsheetDBService.getMusicsheetData(musicsheetId);
        return Response.ok().entity(data).build();
    }

    @Override
    public Response removeLike(@DecimalMin("1") BigDecimal musicsheetId, @DecimalMin("1") BigDecimal userId,
            SecurityContext securityContext) throws NotFoundException {
        UserDBService.removeLike(userId, musicsheetId);
        return Response.ok().build();
    }

    @Override
    public Response updateMusicSheet(MusicsheetMusicsheetIdBody body, @DecimalMin("1") BigDecimal musicsheetId,
            SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
