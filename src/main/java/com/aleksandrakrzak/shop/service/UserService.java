package com.aleksandrakrzak.shop.service;

import com.aleksandrakrzak.shop.domain.dao.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService { // tworzymy interfejs po to aby dobrze zadzaiala klasa proxy

    User save(User user);

    User findById(Long id);

    void delete(Long id);

    Page<User> loadPage(Pageable pageable);

}
// tworzona jest klasa userProxyService ktora implementuje interfejs UserService, wklasie userProxyService tworzona jest instancja
// klasy UserServiceImpl, w kazdej metodzie nadpisanej z interfejsu wywolywana jest metoda ze zmiennej userServiceImpl, a potem
// userProxyService tworzy sie jako bean i ta klasa jest wstrzykiwana a nie userServiceImpl