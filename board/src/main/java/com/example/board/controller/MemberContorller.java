package com.example.board.controller;

import com.example.board.domain.Member;
import com.example.board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class MemberContorller {

    private final MemberService memberService;

    @Autowired // controller
    public MemberContorller(MemberService memberService) {
        this.memberService = memberService;
    }


    @PostMapping("/members/new")
    public String join(MemberForm memberForm){
        Member member = new Member();
        member.setUserId(memberForm.getUserId());
        member.setUserPw(memberForm.getUserPw());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String showAllUserList(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        model.addAttribute("test","HELLO?");
        return "members/memberList";
    }

    @PostMapping("/members/login")
    public String Login(MemberForm memberForm,Model model){

        Optional<Member> optionalMember = memberService.TryLogin(memberForm.getUserId(), memberForm.getUserPw());

        if(optionalMember.isPresent()){
            System.out.println(optionalMember.get().getUserId());
            model.addAttribute("myData" ,optionalMember.get());
            return "/BulletinBoard/index";
        }

        return "redirect:/";

    }


    public class MemberForm {
        private String userId;
        private String userPw;

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setUserPw(String userPw) {
            this.userPw = userPw;
        }

        public String getUserId() {
            return userId;
        }

        public String getUserPw() {
            return userPw;
        }
    }

}
