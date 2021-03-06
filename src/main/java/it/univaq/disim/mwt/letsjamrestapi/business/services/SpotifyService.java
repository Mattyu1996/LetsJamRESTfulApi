package it.univaq.disim.mwt.letsjamrestapi.business.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.core5.http.ParseException;

import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.models.Song;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;

public class SpotifyService {

    private SpotifyApi spotifyApi;
    private ClientCredentialsRequest clientCredentialsRequest;

    public SpotifyService() throws ApiException{
        init();
    }

    public void init() throws ApiException{
        try {
            this.spotifyApi = new SpotifyApi.Builder()
            .setClientId(ConfigGateway.getSpotifyClientId())
            .setClientSecret(ConfigGateway.getSpotifyClientSecret())
            .build();
            this.clientCredentialsRequest = spotifyApi.clientCredentials().build();
            getAccessToken();   
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException(500);
        }
    }
    
    private void getAccessToken() throws ApiException{
        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
            // Set access token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
            System.out.println("Expires in: " + clientCredentials.getExpiresIn());
        } catch (SpotifyWebApiException | ParseException | IOException e) {
            e.printStackTrace();
            throw new ApiException(500);
        }
    }

    public void setSongInfo(Song song) throws ApiException{
        String q = "artist:"+ song.getAuthor() + " track:" + song.getTitle();
        SearchTracksRequest request = spotifyApi.searchTracks(q)
                .build();        
        try {
            Paging<Track> tracks = request.execute();
            
            if(tracks.getItems().length > 0){
                Track traccia = tracks.getItems()[0];
                ArtistSimplified[] artists = traccia.getArtists();
                String author = "";
                for(int i=0; i<artists.length; i++){
                    author += artists[i].getName() + ", ";
                }
                author = author.substring(0,author.length() - 2);
                song.setAuthor(author);
                song.setTitle(traccia.getName());
                song.setImage(traccia.getAlbum().getImages()[0].getUrl());
                song.setAlbumType(traccia.getAlbum().getAlbumType().toString());
                song.setAlbumName(traccia.getAlbum().getName());
                song.setDuration(BigDecimal.valueOf((long) traccia.getDurationMs()));
                song.setIsExplicit(traccia.getIsExplicit());
                song.setSpotifyId(traccia.getId());
            }

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            handleException(e);
        }
    }


    public List<Track> searchSong(String searchString) throws ApiException{
        //String q = "artist:"+ author + " track:" + title;
        SearchTracksRequest request = spotifyApi.searchTracks(searchString).build();
        try {
            Paging<Track> tracks = request.execute();
            List<Track> result = List.of(tracks.getItems());
            return result;
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            handleException(e);
        }
        return new ArrayList<Track>();
    }

    public Song getSongFromSpotifyId(String spotifyId) throws ApiException{
        GetTrackRequest request = spotifyApi.getTrack(spotifyId).build();
        try{
            Track traccia = request.execute();
            ArtistSimplified[] artists = traccia.getArtists();
            String author = "";
            for(int i=0; i<artists.length; i++){
                author += artists[i].getName() + ", ";
            }
            author = author.substring(0,author.length() - 2);
            Song song = new Song();
            song.setAuthor(author);
            song.setTitle(traccia.getName());
            song.setImage(traccia.getAlbum().getImages()[0].getUrl());
            song.setAlbumType(traccia.getAlbum().getAlbumType().toString());
            song.setAlbumName(traccia.getAlbum().getName());
            song.setDuration(BigDecimal.valueOf((long) traccia.getDurationMs()));
            song.setIsExplicit(traccia.getIsExplicit());
            song.setSpotifyId(traccia.getId());
            return song;
        }catch(Exception e){
            handleException(e);
        }
        return null;
    }
    
    public void handleException(Exception e) throws ApiException{
        if(e.getMessage().equals("The access token expired")){
            getAccessToken();
        }
        else{
            e.printStackTrace();
            throw new ApiException(500);
        }
    }
    
}
