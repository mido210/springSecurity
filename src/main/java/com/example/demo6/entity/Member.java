package com.example.demo6.entity;

import com.example.demo6.dto.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.cglib.core.*;

import java.time.*;
import java.time.temporal.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    private String username;
    @JsonIgnore // 제이슨 방식으로 절대 출력하지 않음
    private String password;
    private String email;
        // 프로필사진 이름
    private String profile;
    @Builder.Default
    private LocalDate joinDay = LocalDate.now();
    @Builder.Default
    private Role role = Role.USER;
    @Builder.Default
    private Level level = Level.NOMAL;

    // 로그인 관련된 데이터 : 로그인 실패 횟수, 계정 불록 여부
    private int failedAttempts =0;
    private boolean isLock = false;


    public MemberDto.Read toRead() {
        long days = ChronoUnit.DAYS.between(joinDay,LocalDate.now());
        return new MemberDto.Read(username, email, profile, joinDay,days,level);
    }
}
