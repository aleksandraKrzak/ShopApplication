package com.aleksandrakrzak.shop.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter { // musimy rozszerzyc o te klase bo zwracamy w niej token uzytkownikowi podczas praawidlowewgo logowania

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
        setUsernameParameter("email");
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Claims claims = new DefaultClaims();//slozy do tworzenia tokenoow,  claims  to interfejs a defaultclaims to jego implementacja
        claims.put("authorities", authResult.getAuthorities().stream()   // authResult.getAuthorities() pobiera mi role ktore wstawial m w userdetailservice jako 3 paramtert konstruktora klasy user
                //.map(grantedAuthority -> grantedAuthority.getAuthority())
                .map(GrantedAuthority::getAuthority) //lambda jest wolniejsza bo tworzy klase anonimowa a metoda referencyjna odrazu wywoluje metode
                .collect(Collectors.joining(",")));

        claims.setSubject(authResult.getName())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));

        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "casio")
                .compact();

        response.setHeader("Authorization", "Bearer ".concat(token));
        response.setHeader("Access-Control-Allow-Headers", "Authorization");
        Map<String, String> responseBody = new HashMap<>();

        responseBody.put("authorization", "Bearer ".concat(token));

        new ObjectMapper().writeValue(response.getWriter(), responseBody);
    }
}
