package com.aleksandrakrzak.shop.config;

import com.aleksandrakrzak.shop.auth.JwtAuthenticationFilter;
import com.aleksandrakrzak.shop.auth.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true) // po to aby dzialay adnotacje preauthorized
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsServiceImpl") // dzieki tej adnotacji mowimy jakiego dokladnie beana chcemy wstrzyknac
    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { //klasa do autentykowania uzytkownika w jaki spoosb ma byc autentykowany
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder); // pokazujemy skad ma byc pobrany uzytkownik czy userDetailsService i jakim skryptem haszujemyhaslo
        //uytkownik bedzie dostarczany przez userDetailsService a haslo bedzie hashowane bedzie przez passwordencodera
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // HttpSecurity obiekt ze spring secirity
        http.csrf()
                .ignoringAntMatchers("/**") // wylacza security na wszystkich enpointach po to abysmy sami korzystali z adnotacji preauthorized
                .and()
                .cors()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager()));
    }

}
