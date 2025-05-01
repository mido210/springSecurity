package com.example.demo6.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.lang.reflect.*;
import java.time.*;
import java.util.regex.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    private int pno;
    private String title;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String content;
    private String writer;
    @Builder.Default
    @JsonFormat(pattern = "yyyy년 MM월 dd일 HH시 mm분 ss초")
    private LocalDateTime writeTime = LocalDateTime.now();
    @Builder.Default
    private int readCnt=0;
    @Builder.Default
    private int goodCnt=0;
    @Builder.Default
    private int badCnt=0;
}
