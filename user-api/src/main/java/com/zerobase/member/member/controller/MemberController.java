package com.zerobase.member.member.controller;


import com.zerobase.member.member.dto.LoginDto;
import com.zerobase.member.member.dto.SignUpDto;
import com.zerobase.member.member.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {


    private final MemberService memberService;

    @PostMapping("/api/sign-up")
    public ResponseEntity<Long> signUp(@RequestBody @Valid SignUpDto.SignUpRequestDto request) {
        return ResponseEntity.ok(memberService.registerMember(request));
    }

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDto.LoginRequestDto requestDto) {
        return ResponseEntity.ok(memberService.login(requestDto));
    }

}
