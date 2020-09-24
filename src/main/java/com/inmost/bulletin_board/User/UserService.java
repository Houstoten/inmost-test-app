package com.inmost.bulletin_board.User;

import com.inmost.bulletin_board.User.dto.UserDto;
import com.inmost.bulletin_board.User.dto.UserEditDto;
import com.inmost.bulletin_board.User.dto.UserRegisterDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserDto editUser(UserEditDto dto){
        return UserMapper
                .MAPPER
                .userToUserDto(
                        userRepository
                        .save(UserMapper.MAPPER.userFromEditDto(dto))
                );
    }

    public UserDto saveUser(UserRegisterDto dto) {
        return UserMapper
                .MAPPER
                .userToUserDto(
                        userRepository
                                .save(UserMapper.MAPPER.userFromRegisterUserDto(dto))
                );
    }

    public UserDto getUserDtoByEmail(String email)throws UsernameNotFoundException{
        return userRepository.findByEmail(email).map(UserMapper.MAPPER::userToUserDto)
                .orElseThrow(() -> new UsernameNotFoundException("No such user with email " + email));
    }

    public UserEditDto getUserToEditByEmail(String email) throws UsernameNotFoundException{
        return userRepository.findByEmail(email).map(UserMapper.MAPPER::dtoEditFromUser)
                .orElseThrow(() -> new UsernameNotFoundException("No such user with email " + email));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .map(u -> new org.springframework.security.core.userdetails.User(u.getEmail()
                        , u.getPassword()
                        , List.of())
                )
                .orElseThrow(() -> new UsernameNotFoundException("No such user with email " + email));
    }
}
