package com.example.gmt_timer.domain.timer.controller;

import com.example.gmt_auth.domain.auth.dto.CustomUserDetails;
import com.example.gmt_auth.domain.auth.dto.RockModeDto;
import com.example.gmt_timer.domain.timer.dto.TimerResponseDto;
import com.example.gmt_timer.domain.timer.service.TimerService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timer")
public class TimerController {

    private final TimerService timerService;

    public TimerController(TimerService timerService) {
        this.timerService = timerService;
    }

    @GetMapping("/startTime")
    public String startTimer(){
        timerService.recordStartTime();
        return "공부 시작!";
    }

    @GetMapping("/endTime")
    public String endTimer(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        String email = userDetails.getUsername(); // ← email 반환하게 되어 있음

        long elapsedTime = timerService.recordEndTime(email);
        String realTime = timerService.formatTime(elapsedTime);

        return "공부 완료! " + realTime + "동안 공부를 하였어요!";
    }

    @GetMapping("/list")
    public List<TimerResponseDto> list() {
        return timerService.getTimer()
                .stream()
                .map(TimerResponseDto::new)
                .toList();
    }

    @PostMapping("/rock")
    public boolean rock(@RequestBody RockModeDto rockModeDto) {
        timerService.rockMode(rockModeDto);
        return true;
    }
}
