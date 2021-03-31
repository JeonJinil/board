package com.example.board.service;

import com.example.board.domain.Member;
import com.example.board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {

    private MemberRepository memberRepository;

    @Autowired
    MemberService (MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     * Join method
     */
    public Long join(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member).getId();
    }

    private void validateDuplicateMember(Member member){
       // System.out.print(memberRepository.findByUserId(member.getUserId()).get());
        memberRepository.findByUserId(member.getUserId())
                .ifPresent(m->{
                    throw new IllegalStateException("Already exist member id");
                });
    }

    /**
     *  Return All Members
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * find Member data using id
     */
    public Optional<Member> findOneById (Long id){
        return memberRepository.findById(id);
    }

    /**
     * find Member data using userId
     */
    public Optional<Member> findOneByUserId (String userId){
        return memberRepository.findByUserId(userId);
    }

    /**
     * Try Login
     */
    public Optional<Member> TryLogin(String userId, String userPw){
       return memberRepository.findByUserIdAndUserPw(userId,userPw);
    }
}
