package it.univaq.disim.mwt.letsjamrestapi.business.services;

import java.util.List;

import core.GLA;
import genius.Lyrics;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.models.Song;

public class LyricsService {
    private GLA genius;

    public LyricsService() throws ApiException{
        init();
    }

    public void init() throws ApiException{
        this.genius = new GLA(ConfigGateway.getGeniusClientId(), ConfigGateway.getGeniusAccessToken());
    }

    public void setLyrics(Song song){
        List<Lyrics> testi = this.genius.search(song.getTitle()+" "+song.getAuthor());
        if(testi.size() > 0){
            song.setLyrics(testi.iterator().next().getText());
        }
    }
}
