package com.example.gmt_auth.domain.mail.controller;

import com.example.gmt_auth.domain.auth.service.AuthService;
import com.example.gmt_auth.domain.mail.entity.EmailEntity;
import com.example.gmt_auth.domain.mail.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final AuthService authService;

    @PostMapping("/email/send")
    public ResponseEntity<Void> sendEmailCode(
            @RequestBody EmailSendRequest dto) {

        authService.sendEmailAuthCode(dto.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/email/verify")
    public ResponseEntity<Void> verifyEmailCode(
            @RequestBody EmailVerifyRequest dto) {

        authService.verifyEmailAuthCode(dto.getEmail(), dto.getCode());
        return ResponseEntity.ok().build();
    }
}
