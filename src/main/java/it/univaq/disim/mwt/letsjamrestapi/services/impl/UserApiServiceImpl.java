package it.univaq.disim.mwt.letsjamrestapi.services.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import it.univaq.disim.mwt.letsjamrestapi.business.services.UserDBService;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.ApiException;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.User;
import it.univaq.disim.mwt.letsjamrestapi.models.Genre;
import it.univaq.disim.mwt.letsjamrestapi.models.Instrument;
import it.univaq.disim.mwt.letsjamrestapi.models.UpdateUserBody;
import it.univaq.disim.mwt.letsjamrestapi.services.UserApiService;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.*;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class UserApiServiceImpl extends UserApiService {
    @Override
    public Response addPreferredGenre(@DecimalMin("1") BigDecimal userId, @DecimalMin("1") BigDecimal genreId,
            SecurityContext securityContext) throws NotFoundException, SQLException {
        UserDBService.addUserPreferredGenre(userId, genreId);
        return Response.ok().build();
    }

    @Override
    public Response addPreferredInstrument(@DecimalMin("1") BigDecimal userId, @DecimalMin("1") BigDecimal instrumentId,
            SecurityContext securityContext) throws NotFoundException, SQLException {
        UserDBService.addUserPreferredInstrument(userId, instrumentId);
        return Response.ok().build();
    }

    @Override
    public Response deleteUserById(@DecimalMin("1") BigDecimal userId, SecurityContext securityContext)
            throws NotFoundException, SQLException {
        UserDBService.deleteUser(userId);
        return Response.ok().build();
    }

    @Override
    public Response getUserById(@DecimalMin("1") BigDecimal userId, SecurityContext securityContext)
            throws NotFoundException, SQLException {
        return Response.ok().entity(UserDBService.getUserById(userId)).build();
    }

    @Override
    public Response getUserPreferredGenres(@DecimalMin("1") BigDecimal userId, SecurityContext securityContext)
            throws NotFoundException, SQLException {
        List<Genre> generi = UserDBService.getUserPreferredGenres(userId);
        return Response.ok().entity(generi).build();
    }

    @Override
    public Response getUserPreferredInstruments(@DecimalMin("1") BigDecimal userId, SecurityContext securityContext)
            throws NotFoundException, SQLException {
        List<Instrument> strumenti = UserDBService.getUserPreferredInstruments(userId);
        return Response.ok().entity(strumenti).build();
    }

    @Override
    public Response removePreferredGenre(@DecimalMin("1") BigDecimal userId, @DecimalMin("1") BigDecimal genreId,
            SecurityContext securityContext) throws NotFoundException, SQLException {
        UserDBService.removeUserPreferredGenre(userId, genreId);
        return Response.ok().build();
    }

    @Override
    public Response removePreferredInstrument(@DecimalMin("1") BigDecimal userId,
            @DecimalMin("1") BigDecimal instrumentId, SecurityContext securityContext) throws NotFoundException, SQLException {
        UserDBService.removeUserPreferredInstrument(userId, instrumentId);
        return Response.ok().build();
    }

    @Override
    public Response updateUserAvatar(InputStream stream, SecurityContext securityContext, HttpServletRequest req)
            throws ApiException {
        try {
            OutputStream os = null; 
            User loggedUser = UserDBService.getUserByUsername(securityContext.getUserPrincipal().getName());
            String appName = (req.getContextPath() != "" ) ?  req.getContextPath().substring(1) : "ROOT";
            try {
                String filename = Objects.hash(loggedUser.getEmail(), loggedUser.getId())+".jpg";
                Path basePath = Paths.get((new File(".")).getCanonicalPath());
                String folderPath = basePath.toString().contains("bin") 
                                    ? (new File(Paths.get(basePath.toString(),"..\\","webapps", appName, "uploads").toString())).getCanonicalPath()
                                    : (new File(Paths.get(basePath.toString(),"webapps", appName, "uploads").toString())).getCanonicalPath();
                String filePath = Paths.get(folderPath, filename).toString();
                File uploadFolder = new File(folderPath);
                if (! uploadFolder.exists()) uploadFolder.mkdir();
                File fileToUpload = new File(filePath);
                os = new FileOutputStream(fileToUpload);
                byte[] b = new byte[2048];
                int length;
                while ((length = stream.read(b)) != -1) {
                    os.write(b, 0, length);
                }
                UserDBService.updateUserAvatar(loggedUser.getId(), "/uploads/"+filename);
            }
            finally {
                os.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException(500);
        } 
        return Response.ok().build();
    }

    @Override
    public Response updateUserById(UpdateUserBody body, @DecimalMin("1") BigDecimal userId,
            SecurityContext securityContext) throws NotFoundException, SQLException {
        User u = UserDBService.updateUser(userId, body);
        return Response.ok().entity(u).build();
    }
}
