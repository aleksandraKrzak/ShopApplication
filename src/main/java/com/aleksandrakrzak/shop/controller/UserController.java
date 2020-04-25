package com.aleksandrakrzak.shop.controller;

import com.aleksandrakrzak.shop.domain.dto.UserDto;
import com.aleksandrakrzak.shop.mapper.UserMapper;
import com.aleksandrakrzak.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper; // jest po to aby zmapowac userDTO na user

    @PostMapping
    @PreAuthorize("isAnonymous()") // tylko niezalogowani uzytkownicy maja dostep do tej funkcji
    public UserDto saveUser(@RequestBody UserDto userDto) {  //@RequestBod adnotacja do parametru oznaczajaca ze bedziemy wyciagac z body JSOn i przypisywac do paramteru JAvowego
        return userMapper.userToUserDTO(userService.save(userMapper.userDTOToUser(userDto)));
    }

    @GetMapping("/{id}") //
    @PreAuthorize("@securityServiceImpl.hasAccessToUser(#id) || hasRole('ADMIN')")
    public UserDto findUserById(@PathVariable Long id) { // @PathVariable adnotacja do parametru skorelowane z id w klamarach, nazwa zmiennej oznaczonej taadnotacja musi sie zgadzac z nazwa w {} z GetMappingu
        return userMapper.userToUserDTO(userService.findById(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<UserDto> loadPageUser(@RequestParam Integer page, @RequestParam Integer size) {
        //PageRequest implementuje pageable dlatego moge wstawic w parametrze metode PageRequest.of()
        return userService.loadPage(PageRequest.of(page, size))
                .map(userMapper::userToUserDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id) { // ta funkcja dostepna tylko dla adminow
        userService.delete(id);
    }

}


