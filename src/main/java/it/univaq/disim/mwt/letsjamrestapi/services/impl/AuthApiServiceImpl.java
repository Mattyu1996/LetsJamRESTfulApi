package it.univaq.disim.mwt.letsjamrestapi.services.impl;

import java.security.Key;

import javax.annotation.Generated;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import it.univaq.disim.mwt.letsjamrestapi.business.services.UserDBService;
import it.univaq.disim.mwt.letsjamrestapi.exceptions.NotFoundException;
import it.univaq.disim.mwt.letsjamrestapi.models.AuthLoginBody;
import it.univaq.disim.mwt.letsjamrestapi.models.NewUser;
import it.univaq.disim.mwt.letsjamrestapi.models.User;
import it.univaq.disim.mwt.letsjamrestapi.security.JWTHelpers;
import it.univaq.disim.mwt.letsjamrestapi.services.AuthApiService;

@Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2022-01-07T15:13:39.019Z[GMT]")
public class AuthApiServiceImpl extends AuthApiService {
    @Override
    public Response addUser(NewUser body, SecurityContext securityContext, UriInfo uriInfo) throws NotFoundException {
        User loggedUser = UserDBService.addUser(body);
        String authToken = JWTHelpers.issueToken(uriInfo, loggedUser.getUsername());
        UserDBService.addUserToken(loggedUser.getId(), authToken);
        return Response.ok().entity(loggedUser).header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken).build();
    }
    
    @Override
    public Response login(AuthLoginBody body, SecurityContext securityContext, UriInfo uriInfo) throws NotFoundException {
        try {
            if(UserDBService.authenticateUser(body.getEmail(), body.getPassword())){
                User loggedUser = UserDBService.getUserByEmail(body.getEmail()).get(0);
                String authToken = JWTHelpers.issueToken(uriInfo, loggedUser.getUsername());
                UserDBService.addUserToken(loggedUser.getId(), authToken);
                return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.status(Status.UNAUTHORIZED).build();
    }

    @Override
    public Response logout(SecurityContext securityContext, ContainerRequestContext req) throws NotFoundException {
        String token = req.getHeaderString(HttpHeaders.AUTHORIZATION).substring(("Bearer").length()).trim();
        UserDBService.invalidateToken(token);
        return Response.ok().build();
    }
    @Override
    public Response refreshToken(SecurityContext securityContext, ContainerRequestContext req) throws NotFoundException {
        String token = req.getHeaderString(HttpHeaders.AUTHORIZATION).substring(("Bearer").length()).trim();
        Key key = JWTHelpers.getInstance().getJwtKey();
        Claims jwsc = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        String newToken = JWTHelpers.issueToken(req.getUriInfo(), jwsc.getSubject());
        return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + newToken).build();
    }
}
