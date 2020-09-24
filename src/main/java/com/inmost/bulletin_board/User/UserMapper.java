package com.inmost.bulletin_board.User;

import com.inmost.bulletin_board.User.dto.UserDto;
import com.inmost.bulletin_board.User.dto.UserEditDto;
import com.inmost.bulletin_board.User.dto.UserRegisterDto;
import com.inmost.bulletin_board.User.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);

    User userFromRegisterUserDto(UserRegisterDto userRegisterDto);

    @Mapping(target = "password", ignore = true)
    UserEditDto dtoEditFromUser(User user);

    User userFromEditDto(UserEditDto userEditDto);
}
