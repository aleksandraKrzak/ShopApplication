package com.aleksandrakrzak.shop.service.impl;

import com.aleksandrakrzak.shop.domain.dao.User;
import com.aleksandrakrzak.shop.repository.jpa.UserRepository;
import com.aleksandrakrzak.shop.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;

    @Override
    public boolean hasAccessToUser(Long userId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getMail().equals(email);
        }
        return false;
    }

}
