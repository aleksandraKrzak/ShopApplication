package com.aleksandrakrzak.shop.mapper.impl;

import com.aleksandrakrzak.shop.domain.dao.User;
import com.aleksandrakrzak.shop.domain.dto.UserDto;
import com.aleksandrakrzak.shop.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UseMapperImpl implements UserMapper {

    @Override
    public UserDto userToUserDTO(User user) {
        return UserDto.builder()
                .id(user.getId())
                .age(user.getAge())
                .firstName(user.getFirstName())
                .name(user.getName())
                .lastName(user.getLastName())
                .mail(user.getMail())
                .build();
    }

    @Override
    public User userDTOToUser(UserDto userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .age(userDTO.getAge())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .password(userDTO.getPassword())
                .name(userDTO.getName())
                .mail(userDTO.getMail())
                .build();

    }

    @Override
    public List<UserDto> userListToUserDTOLIst(List<User> userList) {
        return userList.stream()
                //.map(user -> userToUserDTO(user))
                .map(this::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> userListDTOToUserLIst(List<UserDto> userDtoList) {
        return userDtoList.stream()
                .map(this::userDTOToUser)
                .collect(Collectors.toList());
    }

}
