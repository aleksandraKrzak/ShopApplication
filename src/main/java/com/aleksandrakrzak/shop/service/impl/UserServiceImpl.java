package com.aleksandrakrzak.shop.service.impl;

import com.aleksandrakrzak.shop.domain.dao.User;
import com.aleksandrakrzak.shop.repository.jpa.RoleRepository;
import com.aleksandrakrzak.shop.repository.jpa.UserRepository;
import com.aleksandrakrzak.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {// logika biznesowa aplikacji. wstrzykujemy repoxytorium i mappera. service moge stworzyc jako prototype anie singleton, singleton jest domyslnie

    private final UserRepository userRepository; // po to aby miec komnikacje z baza danych

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;// rola czylidostep do funkcjonalsnosci

    @Override
    public User save(User user) { // zwracamy userdto ktore jest potem zwrocone do kontrolera
        user.setPassword(passwordEncoder.encode(user.getPassword())); // userowi ktorego chce zapisac do bazy danych ustawiam schashowane haslo, bo nie wolno zapisaywac nie schashowanych hasel do bd
        roleRepository.findByName("ROLE_USER").ifPresent(role -> user.setRole(Collections.singletonList(role))); // Collections.singletonList(role) tworzy liste z jednym obiektem
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id) // mozemy wywolac funkcje map bo to optional jest
                //.map(user -> userMapper.userToUserDTO(user))
                .orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " does not exist")); //orElsThrow zwraca obiekt z optionala jesli istnieje a jesli nie istnieje to zwraca wyjatek z suppliera
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> loadPage(Pageable pageable) {
        //1. pobieramby page z bazy danych
        //2. tworzymy nowego page w ktorym zwracamy obiekt dto a nie bazodanowy
        return userRepository.findAll(pageable);

        //all.getPageable() all.getTotalElements() musze tak napisac zamiast pageable gdyz musze dostac WSZYTSKIE informacje o stronie, a bez tego praktycznie wgl nie dostane informacji
        //return new PageImpl<>(userMapper.userListToUserDTOLIst(all.getContent()), all.getPageable(), all.getTotalElements());
        // 1szy argument to conetent jaki strona przyjmuje, 2gi argumnet to pageable z page bazodanowego, ilosc ilosc elementow dla danego zapytania
    }

}