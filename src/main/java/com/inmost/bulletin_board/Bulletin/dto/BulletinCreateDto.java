package com.inmost.bulletin_board.Bulletin.dto;

import com.inmost.bulletin_board.User.dto.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.Optional;

@Data
@NoArgsConstructor
public class BulletinCreateDto {
    @NotEmpty
    private String header;

    private Optional<MultipartFile> image;

    @NotEmpty
    private String description;

    private UserDto userDto;
}
