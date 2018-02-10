package com.bristech.security;

import io.jsonwebtoken.Jwts;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static com.bristech.config.JWTConfiguration.*;


class AuthorizationFilter extends BasicAuthenticationFilter {

    private static final Logger log = Logger.getLogger(AuthenticationFilter.class);


    AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        log.info("Attempting to authorise user");

        //getting header and checking if it's valid
        String header = request.getHeader(HEADER);
        if(header == null){
            log.error("Could not find token in the header");
            chain.doFilter(request, response);
            return;
        }
        //

        //getting the token from the header and porting it
        UsernamePasswordAuthenticationToken token = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request, response);
        //
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER);
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SECRET.getBytes())
                    .parseClaimsJws(token)
                    .getBody()
                    .get(CLAIM_USER_NAME, String.class);

            log.info("Authenticating user:" + user);


            if (user != null)
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
        return null;
    }
}
