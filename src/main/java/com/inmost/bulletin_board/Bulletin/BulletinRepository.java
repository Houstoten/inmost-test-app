package com.inmost.bulletin_board.Bulletin;

import com.inmost.bulletin_board.Bulletin.model.Bulletin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BulletinRepository extends JpaRepository<Bulletin, UUID> {
}
