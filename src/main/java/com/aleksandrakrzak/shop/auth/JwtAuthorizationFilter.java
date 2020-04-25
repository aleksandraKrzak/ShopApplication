package com.aleksandrakrzak.shop.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            SecurityContextHolder.getContext().setAuthentication(null);
            chain.doFilter(request, response);
            return;
        }

        Claims claims = Jwts.parser() // rozparsowujemy token
                .setSigningKey("casio") // po kluczu hashowany jest moj uzytkownik
                .parseClaimsJws(token.replace("Bearer ", "")) // wstawaim token ktory wygenerowalam w poprzednim filtrze
                .getBody();

        String authorities = claims.get("authorities", String.class);

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        if (authorities != null) {
            authorityList = Arrays.stream(authorities.split(","))
                    //.map(roleName -> new SimpleGrantedAuthority(roleName))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }

        String email = claims.getSubject(); // pobieramy subject

        if (email != null) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, null, authorityList);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            chain.doFilter(request, response);
        } else {
            SecurityContextHolder.getContext().setAuthentication(null);
            response.setStatus(401);
        }
    }

}
