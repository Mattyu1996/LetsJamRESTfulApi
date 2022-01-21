package it.univaq.disim.mwt.letsjamrestapi;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import it.univaq.disim.mwt.letsjamrestapi.resources.AuthApi;
import it.univaq.disim.mwt.letsjamrestapi.resources.CommentApi;
import it.univaq.disim.mwt.letsjamrestapi.resources.GenresApi;
import it.univaq.disim.mwt.letsjamrestapi.resources.InstrumentsApi;
import it.univaq.disim.mwt.letsjamrestapi.resources.JacksonJsonProvider;
import it.univaq.disim.mwt.letsjamrestapi.resources.MusicsheetApi;
import it.univaq.disim.mwt.letsjamrestapi.resources.MusicsheetsApi;
import it.univaq.disim.mwt.letsjamrestapi.resources.ScoreApi;
import it.univaq.disim.mwt.letsjamrestapi.resources.SongApi;
import it.univaq.disim.mwt.letsjamrestapi.resources.SongsApi;
import it.univaq.disim.mwt.letsjamrestapi.resources.UserApi;
import it.univaq.disim.mwt.letsjamrestapi.resources.UsersApi;
import it.univaq.disim.mwt.letsjamrestapi.security.AppExceptionMapper;
import it.univaq.disim.mwt.letsjamrestapi.security.AuthLevel1Filter;
import it.univaq.disim.mwt.letsjamrestapi.security.CORSFilter;

@ApplicationPath("rest")
public class RESTApp extends Application {

    private final Set<Class<?>> classes;

    public RESTApp() {
        HashSet<Class<?>> c = new HashSet<Class<?>>();
        // Risorse
        c.add(UsersApi.class);
        c.add(UserApi.class);
        c.add(SongsApi.class);
        c.add(SongApi.class);
        c.add(ScoreApi.class);
        c.add(MusicsheetApi.class);
        c.add(MusicsheetsApi.class);
        c.add(InstrumentsApi.class);
        c.add(GenresApi.class);
        c.add(CommentApi.class);
        c.add(AuthApi.class);

        // Provider Jackson per la serializzazione delle date
        c.add(JacksonJsonProvider.class);

        // Filtro che gestisce l'autenticazione
        c.add(AuthLevel1Filter.class);

        // Filtro che gestisce gli header CORS
        c.add(CORSFilter.class);

        // ExceptionMapper
        c.add(AppExceptionMapper.class);

        classes = Collections.unmodifiableSet(c);
    }

    // l'override di questo metodo deve restituire il set
    // di classi che Jersey utilizzerà per pubblicare il
    // servizio. Tutte le altre, anche se annotate, verranno
    // IGNORATE
    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}