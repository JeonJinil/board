package com.example.board.service;

import com.example.board.domain.Member;
import com.example.board.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void join() throws Exception {
        //given
        Member member = new Member();
        member.setUserId("spring11");
        member.setUserPw("java11");

        //when
        Long saveId = memberService.join(member);
        System.out.println("Saved ID  = "+saveId);
        //then
        Member result = memberService.findOneById(saveId).get();
        Assertions.assertThat(result.getUserId()).isEqualTo(member.getUserId());
        Assertions.assertThat(result.getUserPw()).isEqualTo(member.getUserPw());
    }

    @Test
    public void ValidateDuplicateUserId(){
        Member member1 = new Member();
        member1.setUserId("spring11");
        member1.setUserPw("java11");

        Member member2 = new Member();
        member2.setUserId("spring11");
        member2.setUserPw("java21");

        memberService.join(member1);

        IllegalStateException e = org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("Already exist member id");

    }

    @Test
    public void TryLogin(){
        //given
        Member member = new Member();
        member.setUserId("spring11");
        member.setUserPw("java11");
        //when
        Long saveId = memberService.join(member);

        //then
        Member result = memberService.TryLogin(member.getUserId(),member.getUserPw()).get();
        Assertions.assertThat(result.getUserPw()).isEqualTo(member.getUserPw());
        Assertions.assertThat(result.getUserId()).isEqualTo(member.getUserId());
        System.out.println("Get ID  = "+result.getUserId());
        System.out.println("Get PW  = "+result.getUserPw());
    }
}
