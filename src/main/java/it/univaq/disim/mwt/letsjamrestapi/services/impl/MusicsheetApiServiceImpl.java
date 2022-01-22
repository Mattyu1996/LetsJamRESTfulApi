package it.univaq.disim.mwt.letsjamrestapi.services.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;
import javax.validation.constraints.DecimalMin;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import it.univaq.disim.mwt.letsjamrestapi.business.services.CommentDBService;
import it.univaq.disim.mwt.letsjamrestapi.business.services.GenreDBService;
import it.univaq.disim.mwt.letsjamrestapi.business.services.LyricsService;
import it.univaq.disim.mwt.letsjamrestapi.business.services.MusicsheetDBService;
import it.univaq.disim.mwt.letsjamrestapi.business.services.ScoreAnalyzerService;
import it.univaq.disim.mwt.letsjamrestapi.business.services.SongDBService;
import it.univaq.disim.mwt.letsjamrestapi.business.services.SpotifyService;
import it.univaq.disim.mwt.letsjamrestapi.business.services.UserDBService;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.Genre;
import it.univaq.disim.mwt.letsjamrestapi.models.Instrument;
import it.univaq.disim.mwt.letsjamrestapi.models.MusicSheet;
import it.univaq.disim.mwt.letsjamrestapi.models.MusicSheetData;
import it.univaq.disim.mwt.letsjamrestapi.models.Comment;
import it.univaq.disim.mwt.letsjamrestapi.models.CommentBody;
import it.univaq.disim.mwt.letsjamrestapi.models.UpdateMusicsheetBody;
import it.univaq.disim.mwt.letsjamrestapi.models.User;
import it.univaq.disim.mwt.letsjamrestapi.models.NewMusicSheet;
import it.univaq.disim.mwt.letsjamrestapi.models.Song;
import it.univaq.disim.mwt.letsjamrestapi.services.MusicsheetApiService;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class MusicsheetApiServiceImpl extends MusicsheetApiService {

    @Override
    public Response addComment(@DecimalMin("1") BigDecimal musicsheetId, CommentBody body,
            BigDecimal parent, SecurityContext securityContext) throws ApiException {
        try {
            CommentDBService.addCommentToMusicsheet(musicsheetId, parent, body, BigDecimal.valueOf(4));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApiException(500);        }
        return Response.ok().build();
    }

    @Override
    public Response addLike(@DecimalMin("1") BigDecimal musicsheetId, @DecimalMin("1") BigDecimal userId,
            SecurityContext securityContext) throws NotFoundException, SQLException {
        UserDBService.addLike(userId, musicsheetId);
        return Response.ok().build();
    }

    @Override
    public Response addMusicSheet(NewMusicSheet body, SecurityContext securityContext) throws SQLException, ApiException {

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
        User loggedUser = UserDBService.getUserByUsername(securityContext.getUserPrincipal().getName());
        spartito.setUser(loggedUser);
        spartito.setRearranged(body.isRearranged());
        spartito.setVerified(false);
        spartito.setVisibility(body.isVisibility());

        Song s = new Song();
        System.out.println(body.getSong().getSpotifyId() != null);

        if (body.getSong().getId() != null) {
            s = SongDBService.getSongById(body.getSong().getId());
        } else if (body.getSong().getSpotifyId() != null) {
            SpotifyService spotify = new SpotifyService();
            LyricsService genius = new LyricsService();
            s = spotify.getSongFromSpotifyId(body.getSong().getSpotifyId());
            genius.setLyrics(s);
            Genre g = GenreDBService.getGenreById(body.getSong().getGenreId());
            s.setGenre(g);
            SongDBService.addSong(s);
        } else {
            s.setTitle(body.getSong().getTitle());
            s.setAuthor(body.getSong().getAuthor());
            Genre g = GenreDBService.getGenreById(body.getSong().getGenreId());
            s.setGenre(g);
            SongDBService.addSong(s);
        }
        spartito.setSong(s);

        BigDecimal id = MusicsheetDBService.addMusicSheet(spartito);
        MusicsheetDBService.addMusicSheetData(data, id);

        return Response.ok().entity(MusicsheetDBService.getMusicsheetById(id)).build();
    }

    @Override
    public Response deleteMusicSheetById(@DecimalMin("1") BigDecimal musicsheetId, SecurityContext securityContext)
            throws ApiException {
        try {
            MusicsheetDBService.deleteMusicSheet(musicsheetId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApiException(500);
        }
        return Response.ok().build();
    }

    @Override
    public Response getMusicSheetById(@DecimalMin("1") BigDecimal musicsheetId, SecurityContext securityContext)
            throws ApiException {
        MusicSheet spartito;
        try {
            spartito = MusicsheetDBService.getMusicsheetById(musicsheetId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApiException(500);
        }
        return Response.ok().entity(spartito).build();
    }

    @Override
    public Response getMusicSheetComments(@DecimalMin("1") BigDecimal musicsheetId, SecurityContext securityContext)
            throws ApiException {
        List<Comment> commenti;
        try {
            commenti = CommentDBService.getMusicsheetComments(musicsheetId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApiException(500);
        }
        return Response.ok().entity(commenti).build();
    }

    @Override
    public Response getMusicSheetData(@DecimalMin("1") BigDecimal musicsheetId, SecurityContext securityContext)
            throws ApiException {
        MusicSheetData data = MusicsheetDBService.getMusicsheetData(musicsheetId);
        return Response.ok().entity(data).build();
    }

    @Override
    public Response removeLike(@DecimalMin("1") BigDecimal musicsheetId, @DecimalMin("1") BigDecimal userId,
            SecurityContext securityContext) throws NotFoundException, SQLException {
        UserDBService.removeLike(userId, musicsheetId);
        return Response.ok().build();
    }

    @Override
    public Response updateMusicSheet(UpdateMusicsheetBody body, @DecimalMin("1") BigDecimal musicsheetId,
            SecurityContext securityContext) throws ApiException {
        try {
            MusicsheetDBService.updateMusicSheet(body, musicsheetId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApiException(500);        }
        return Response.ok().build();
    }
}
