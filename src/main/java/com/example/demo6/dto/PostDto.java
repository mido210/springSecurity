package com.example.demo6.dto;

import com.example.demo6.entity.*;
import lombok.*;

import java.util.*;

// PostDto들을 담는 클래스 -> 클래스 수를 줄이기 위함
public class PostDto {
    //페이징 출력 DTO
    @Data
    @AllArgsConstructor
    public static class Pages{
        private int prev;
        private int start;
        private int end;
        private int next;
        private int pageno;
        private List<Post> posts;
    }

    public static class Create{

    }
}
