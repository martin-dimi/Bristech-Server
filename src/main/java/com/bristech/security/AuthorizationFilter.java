package com.bristech.security;

import io.jsonwebtoken.Jwts;
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

import static com.bristech.security.AuthenticationFilter.HEADER;
import static com.bristech.security.AuthenticationFilter.SECRET;
import static java.util.Collections.emptyList;

public class AuthorizationFilter extends BasicAuthenticationFilter {


    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        //getting header and checking if it's valid
        String header = request.getHeader(HEADER);
        if(header == null){
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
                    .getSubject();

            System.out.println("------ " + user);
            if (user != null)
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
        return null;
    }
}
