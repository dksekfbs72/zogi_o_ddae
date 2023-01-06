package com.zerobase.member.member.service;

import static com.zerobase.member.exception.ErrorCode.MEMBER_NOT_FOUND;

import com.zerobase.member.exception.CustomException;
import com.zerobase.member.jwt.JwtTokenProvider;
import com.zerobase.member.member.dto.LoginDto.LoginRequestDto;
import com.zerobase.member.member.dto.SignUpDto.SignUpRequestDto;
import com.zerobase.member.member.entity.Member;
import com.zerobase.member.member.repository.MemberRepository;
import com.zerobase.member.member.type.MemberRole;
import com.zerobase.member.member.type.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {


    private final MemberRepository memberRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public Long registerMember(SignUpRequestDto requestDto) {
        Member newMember = Member.builder()
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .password(bCryptPasswordEncoder.encode(requestDto.getPassword()))
                .status(MemberStatus.ACTIVE)
                .roles(MemberRole.ROLE_USER)
                .build();

        memberRepository.save(newMember);

        return newMember.getId();
    }


    public String login(LoginRequestDto requestDto) {
        Member loginMember = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

        return jwtTokenProvider.createToken(loginMember.getEmail(), loginMember.getRoles());
    }
}



