package com.inmost.bulletin_board.Bulletin;

import com.inmost.bulletin_board.Bulletin.dto.BulletinCreateDto;
import com.inmost.bulletin_board.Bulletin.dto.BulletinDto;
import com.inmost.bulletin_board.Bulletin.model.Bulletin;
import com.inmost.bulletin_board.User.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UserMapper.class})
public interface BulletinMapper {
    BulletinMapper MAPPER = Mappers.getMapper(BulletinMapper.class);

    @Mapping(target = "header", source = "dto.header")
    @Mapping(target = "image", source = "imagePath")
    @Mapping(target = "description", source = "dto.description")
    @Mapping(target = "bulletinHolder", source = "dto.userDto")
    Bulletin bulletinFromCreateDto(BulletinCreateDto dto, String imagePath);

    @Mapping(target = "userName", source = "bulletinHolder.email")
    BulletinDto dtoFromBulletin(Bulletin bulletin);
}
