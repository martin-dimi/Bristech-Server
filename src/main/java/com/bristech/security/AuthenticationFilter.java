package com.bristech.security;

import com.bristech.entities.User;
import com.bristech.entities.UserCredentials;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.bristech.config.JWTConfiguration.*;


class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger log = Logger.getLogger(AuthenticationFilter.class);

    private final AuthenticationManager authenticationManager;

    AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * On user login request, this function calls, extracts the user data and authenticates them
     **/
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) {
        try {
            log.info("Attempting to authenticate");
            User user = new ObjectMapper().readValue(req.getInputStream(), User.class);

            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>());
            token.setDetails(user);

            //Authenticating the user
            return authenticationManager.authenticate(token);

        } catch (IOException e) {
            log.error("Error authenticating: " + e);
            return new UsernamePasswordAuthenticationToken(null, null, null);
        }
    }

    /**
     * Handles token creation
     * Puts the user_id and user_name in the token
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {

        //Extracting the user
        UserCredentials user = (UserCredentials) authResult.getPrincipal();

        //Creating the token
        String token = Jwts.builder()
                .claim(CLAIM_USER_ID, user.getId())
                .claim(CLAIM_USER_NAME, user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP_DATE))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();

        log.info("User_id=" + user.getId() + " authenticated");
        //Adding token to the header of the response
        response.addHeader(HEADER, token);
    }
}
