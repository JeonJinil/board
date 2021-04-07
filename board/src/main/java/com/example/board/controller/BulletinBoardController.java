package com.example.board.controller;

import com.example.board.domain.BulletinBoard;
import com.example.board.service.BulletinBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class BulletinBoardController {
    private BulletinBoardService bulletinBoardService;

    @Autowired
    public BulletinBoardController(BulletinBoardService bulletinBoardService) {
        this.bulletinBoardService = bulletinBoardService;
    }

    @GetMapping("/BulletinBoard")
    public String ShowBoardList(Model model){
        List<BulletinBoard> boards = bulletinBoardService.findAllBoard();
        model.addAttribute("boards",boards);
        
        return "BulletinBoard/index";
    }

    @GetMapping("/BulletinBoard/show")
    public String ShowBoard(@RequestParam(value = "boardId")Long boardId, Model model){
        BulletinBoard bulletinBoard = bulletinBoardService.findById(boardId).get();
        model.addAttribute("board",bulletinBoard);
        return "BulletinBoard/showBoard";
    }

    @GetMapping("/BulletinBoard/delete")
    public String DeleteBoard(@RequestParam(value = "boardId")Long boardId, Model model){
        System.out.println("??"+boardId);
        bulletinBoardService.deleteById(boardId);
        return ShowBoardList(model);
    }

    @PostMapping("/BulletinBoard/write")
    public String WriteBoard(WriteBoardForm writeBoardForm, Model model){
        bulletinBoardService.Write(writeBoardForm.getTitle(), writeBoardForm.getContent(),8L, writeBoardForm.getWriteDate());
        return ShowBoardList(model);
    }

    @GetMapping("/BulletinBoard/writeBoardForm")
    public String WriteForm(){
        return "BulletinBoard/writeBoardForm";
    }

    @GetMapping("/BulletinBoard/modify")
    public String ModifyBoard(@RequestParam(value = "boardId")Long boardId, Model model){
        BulletinBoard bulletinBoard = bulletinBoardService.findById(boardId).get();
        model.addAttribute("board",bulletinBoard);
        return "BulletinBoard/modifyBoard";
    }

    @PostMapping("/BulletinBoard/modify")
    public String finishModifyBoard(ModifBoardForm modifBoardForm, Model model){
        System.out.println("modifBoardForm = " + modifBoardForm);
        bulletinBoardService.updateTitleAndContent(modifBoardForm.getTitle(), modifBoardForm.getContent(), modifBoardForm.getBoardId());
        return ShowBoardList(model);
    }

    class WriteBoardForm {
        private String title;
        private String content;
        private Date writeDate;

        public WriteBoardForm(String title, String content) {
            this.title = title;
            this.content = content;
            this.writeDate = new Date();
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Date getWriteDate() {
            return writeDate;
        }

        public void setWriteDate(Date writeDate) {
            this.writeDate = writeDate;
        }
    }

    class ModifBoardForm {
        private String title;
        private String content;
        private Long boardId;

        @Override
        public String toString() {
            return "ModifBoardForm{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", boardId=" + boardId +
                    '}';
        }

        public ModifBoardForm(String title, String content, Long boardId ) {
            this.title = title;
            this.content = content;
            this.boardId = boardId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Long getBoardId() {
            return boardId;
        }

        public void setBoardId(Long boardId) {
            this.boardId = boardId;
        }
    }

}
