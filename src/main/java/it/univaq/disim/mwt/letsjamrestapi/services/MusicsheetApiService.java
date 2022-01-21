package it.univaq.disim.mwt.letsjamrestapi.services;

import java.math.BigDecimal;
import java.sql.SQLException;

import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.CommentBody;
import it.univaq.disim.mwt.letsjamrestapi.models.UpdateMusicsheetBody;
import it.univaq.disim.mwt.letsjamrestapi.models.NewMusicSheet;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.annotation.Generated;
import javax.validation.constraints.*;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public abstract class MusicsheetApiService {
    public abstract Response addComment(@DecimalMin("1") BigDecimal musicsheetId, CommentBody body,
            BigDecimal parent, SecurityContext securityContext) throws NotFoundException, ApiException;

    public abstract Response addLike(@DecimalMin("1") BigDecimal musicsheetId, @DecimalMin("1") BigDecimal userId,
            SecurityContext securityContext) throws NotFoundException, SQLException;

    public abstract Response addMusicSheet(NewMusicSheet body, SecurityContext securityContext)
            throws NotFoundException, SQLException, ApiException;

    public abstract Response deleteMusicSheetById(@DecimalMin("1") BigDecimal musicsheetId,
            SecurityContext securityContext) throws NotFoundException, ApiException;

    public abstract Response getMusicSheetById(@DecimalMin("1") BigDecimal musicsheetId,
            SecurityContext securityContext) throws NotFoundException, ApiException;

    public abstract Response getMusicSheetComments(@DecimalMin("1") BigDecimal musicsheetId,
            SecurityContext securityContext) throws NotFoundException, ApiException;

    public abstract Response getMusicSheetData(@DecimalMin("1") BigDecimal musicsheetId,
            SecurityContext securityContext) throws NotFoundException, ApiException;

    public abstract Response removeLike(@DecimalMin("1") BigDecimal musicsheetId, @DecimalMin("1") BigDecimal userId,
            SecurityContext securityContext) throws NotFoundException, SQLException;

    public abstract Response updateMusicSheet(UpdateMusicsheetBody body, @DecimalMin("1") BigDecimal musicsheetId,
            SecurityContext securityContext) throws NotFoundException, ApiException;
}
