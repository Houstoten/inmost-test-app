package com.inmost.bulletin_board.User.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class UserRegisterDto {
    private String firstName;
    private String lastName;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    private String password;
}
