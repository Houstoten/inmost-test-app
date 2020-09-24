package com.inmost.bulletin_board.User.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserEditDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String newPassword;
}
