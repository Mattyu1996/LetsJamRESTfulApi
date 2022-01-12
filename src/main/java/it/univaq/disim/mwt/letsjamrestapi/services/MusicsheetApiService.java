package it.univaq.disim.mwt.letsjamrestapi.services;

import java.math.BigDecimal;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.MusicsheetIdCommentBody;
import it.univaq.disim.mwt.letsjamrestapi.models.MusicsheetMusicsheetIdBody;
import it.univaq.disim.mwt.letsjamrestapi.models.NewMusicSheet;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.annotation.Generated;
import javax.validation.constraints.*;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public abstract class MusicsheetApiService {
    public abstract Response addComment(@DecimalMin("1") BigDecimal musicsheetId, MusicsheetIdCommentBody body,
            BigDecimal parent, SecurityContext securityContext) throws NotFoundException;

    public abstract Response addLike(@DecimalMin("1") BigDecimal musicsheetId, @DecimalMin("1") BigDecimal userId,
            SecurityContext securityContext) throws NotFoundException;

    public abstract Response addMusicSheet(NewMusicSheet body, SecurityContext securityContext)
            throws NotFoundException;

    public abstract Response deleteMusicSheetById(@DecimalMin("1") BigDecimal musicsheetId,
            SecurityContext securityContext) throws NotFoundException;

    public abstract Response getMusicSheetById(@DecimalMin("1") BigDecimal musicsheetId,
            SecurityContext securityContext) throws NotFoundException;

    public abstract Response getMusicSheetComments(@DecimalMin("1") BigDecimal musicsheetId,
            SecurityContext securityContext) throws NotFoundException;

    public abstract Response getMusicSheetData(@DecimalMin("1") BigDecimal musicsheetId,
            SecurityContext securityContext) throws NotFoundException;

    public abstract Response removeLike(@DecimalMin("1") BigDecimal musicsheetId, @DecimalMin("1") BigDecimal userId,
            SecurityContext securityContext) throws NotFoundException;

    public abstract Response updateMusicSheet(MusicsheetMusicsheetIdBody body, @DecimalMin("1") BigDecimal musicsheetId,
            SecurityContext securityContext) throws NotFoundException;
}