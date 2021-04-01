package com.example.board.service;

import com.example.board.domain.BulletinBoard;
import com.example.board.repository.BulletinBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BulletinBoardService {
    private BulletinBoardRepository bulletinBoardRepository ;

    @Autowired
    public BulletinBoardService(BulletinBoardRepository bulletinBoardRepository) {
        this.bulletinBoardRepository = bulletinBoardRepository;
    }

    public Long Write(String title, String content, Long memberId, Date writeDate){
        BulletinBoard bulletinBoard = new BulletinBoard(title,content,memberId,writeDate);
        return bulletinBoardRepository.save(bulletinBoard).getId();
    }

    public List<BulletinBoard> findAllBoard (){
        return bulletinBoardRepository.findAll();
    }

    public Optional<BulletinBoard> findById(Long id){
        return bulletinBoardRepository.findById(id);
    }

}
