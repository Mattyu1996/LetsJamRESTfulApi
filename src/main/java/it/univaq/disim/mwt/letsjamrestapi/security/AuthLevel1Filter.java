package it.univaq.disim.mwt.letsjamrestapi.security;

import java.io.IOException;
import java.security.Key;
import java.security.Principal;
import java.util.Date;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import it.univaq.disim.mwt.letsjamrestapi.business.services.UserDBService;
import it.univaq.disim.mwt.letsjamrestapi.models.User;
import it.univaq.disim.mwt.letsjamrestapi.models.User.RoleEnum;

@Provider
@AuthLevel1
@Priority(Priorities.AUTHENTICATION)
public class AuthLevel1Filter implements ContainerRequestFilter{

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String token = null;
        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(("Bearer").length()).trim();
        }
        if(token != null && !token.isEmpty()){
            final String username = validateToken(token);
            User user = UserDBService.getUserFromToken(token);
            if(username != null && user != null){
                System.out.println("AUTORIZZATO");
                requestContext.setSecurityContext(new SecurityContext() {
                    @Override
                    public Principal getUserPrincipal() {
                        return new Principal() {
                            @Override
                            public String getName() {
                                return username;
                            }
                        };
                    }

                    @Override
                    public boolean isUserInRole(String role) {
                        return user.getRole().equals(RoleEnum.UTENTE);
                    }

                    @Override
                    public boolean isSecure() {
                        return true;
                    }

                    @Override
                    public String getAuthenticationScheme() {
                        return "Token-Based-Auth-Scheme";
                    }
                });
            }
            else{
                System.out.println("NON AUTORIZZATO");
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }
        else{
            System.out.println("NON AUTORIZZATO");
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private String validateToken(String token){
        try {
            Key key = JWTHelpers.getInstance().getJwtKey();
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody(); 
            return claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
