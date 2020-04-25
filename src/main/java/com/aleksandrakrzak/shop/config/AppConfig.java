package com.aleksandrakrzak.shop.config;

import com.aleksandrakrzak.shop.domain.dao.Role;
import com.aleksandrakrzak.shop.repository.jpa.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration // w tej klasie tworzymy beany
public class AppConfig {

    @Bean// bean ktory zostanie uruchomiony po urchomieni aplikacji
    public CommandLineRunner commandLineRunner(RoleRepository roleRepository) { // metoda dodaje role jesli nie istnieje, msimy wywolac meode szkajaca po name roli i jesli rola nie istnieje to zapisjemy ja do bazy  danych. bedziemy to robic na role user i role admin
        return args -> {
            Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
            if (!optionalRoleUser.isPresent()) {
                roleRepository.save(Role.builder()
                        .name("ROLE_USER").build());
            }

            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            if (!optionalRoleAdmin.isPresent()) {
                roleRepository.save(Role.builder()
                        .name("ROLE_ADMIN").build());
            }
        };
    }

    // 1. Tworzymy bean ktory bedzie zwracal password encoder, password encorder jest infersjem a jego implementacja jest backcryp password encoder

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
