package com.example.demo6.entity;

import lombok.*;

@Getter
@AllArgsConstructor
public enum Level {
    NOMAL("고마운 분") , SIVER("귀한분"), GOLD("천생연분");

    // enum에 한글화 파라미터를 추가하면 , 추가한 한글 객체??를 생성
    private final String name;
}
