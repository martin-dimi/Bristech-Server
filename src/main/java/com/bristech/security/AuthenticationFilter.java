package com.bristech.security;

import com.bristech.entities.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    //TODO Move to properties
    public static final long TOKEN_EXP_DATE = 864_000_000;
    public static final String SECRET = "you're gay";
    public static final String HEADER = "auth_token";

    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    //Checks whether the user is valid
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            AppUser appUser = new ObjectMapper()
                    .readValue(req.getInputStream(), AppUser.class);

            // TODO checkout authorities
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            appUser.getUsername(),
                            appUser.getPassword(),
                            new ArrayList<>())


            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String token = Jwts.builder()
                .setSubject(((User) (authResult.getPrincipal())).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP_DATE))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();

        response.addHeader(HEADER, token);
    }
}
