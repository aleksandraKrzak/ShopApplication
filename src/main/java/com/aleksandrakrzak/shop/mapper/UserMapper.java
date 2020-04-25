package com.aleksandrakrzak.shop.mapper;

import com.aleksandrakrzak.shop.domain.dao.User;
import com.aleksandrakrzak.shop.domain.dto.UserDto;

import java.util.List;

public interface UserMapper {

    UserDto userToUserDTO(User user);

    User userDTOToUser(UserDto userDTO);

    List<UserDto> userListToUserDTOLIst(List<User> userList);

    List<User> userListDTOToUserLIst(List<UserDto> userDtoList);

}
