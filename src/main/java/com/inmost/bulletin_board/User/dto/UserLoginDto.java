package com.inmost.bulletin_board.User.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginDto {
    private String email;
    private String password;
}
