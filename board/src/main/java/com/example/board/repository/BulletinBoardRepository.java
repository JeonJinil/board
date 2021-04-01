package com.example.board.repository;

import com.example.board.domain.BulletinBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BulletinBoardRepository extends JpaRepository <BulletinBoard,Long> {
    BulletinBoard save(BulletinBoard bulletinBoard);
    Optional<BulletinBoard> findById(Long id);
    List<BulletinBoard> findByMemberId(Long member_id);
    List<BulletinBoard> findAll();
}
