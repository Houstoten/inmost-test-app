package com.inmost.bulletin_board.Bulletin.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class BulletinDto {

    private UUID id;

    private String header;

    private String image;

    private String description;

    private Date createdAt;

    private String userName;
}
