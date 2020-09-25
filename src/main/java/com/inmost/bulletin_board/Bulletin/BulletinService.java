package com.inmost.bulletin_board.Bulletin;

import com.inmost.bulletin_board.Bulletin.dto.BulletinCreateDto;
import com.inmost.bulletin_board.Bulletin.dto.BulletinDto;
import com.inmost.bulletin_board.FileSystem.FileSystemService;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BulletinService {

    private final BulletinRepository bulletinRepository;
    private final FileSystemService fileSystemService;

    public BulletinService(BulletinRepository bulletinRepository, FileSystemService fileSystemService) {
        this.bulletinRepository = bulletinRepository;
        this.fileSystemService = fileSystemService;
    }

    public Page<BulletinDto> getBulletins(Integer page, Integer size){
        return bulletinRepository
                .findAll(PageRequest.of(page, size))
                .map(BulletinMapper.MAPPER::dtoFromBulletin);
    }

    public List<BulletinDto> getBulletins(){
        return bulletinRepository
                .findAll()
                .stream()
                .map(BulletinMapper.MAPPER::dtoFromBulletin)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public BulletinDto addBulletin(BulletinCreateDto dto) {
        String imagePath = null;
        if (dto.getImage().isPresent()) {
            imagePath = fileSystemService
                    .saveFile(
                            dto
                                    .getImage()
                                    .get()
                                    .getBytes()
                    ).toString();
        }

        return BulletinMapper.MAPPER.dtoFromBulletin(
                bulletinRepository
                        .save(
                                BulletinMapper.MAPPER.bulletinFromCreateDto(dto, imagePath)
                        )
        );
    }
}
