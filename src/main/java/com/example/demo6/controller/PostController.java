package com.example.demo6.controller;

import com.example.demo6.dto.*;
import com.example.demo6.entity.*;
import com.example.demo6.service.*;
import io.swagger.v3.oas.annotations.*;
import jakarta.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.security.*;
import java.util.*;

@Validated
@RestController
public class PostController {
    @Autowired
    private PostService service;

//    @PostConstruct
//    public void init(){
//        System.out.println(service);
//    }

    @Operation(summary = "페이징", description = "기본 페이지 1, 페이지 크기 10으로 페이징")
    @GetMapping("/posts")
    public ResponseEntity<PostDto.Pages> findAll(@RequestParam(defaultValue = "1") int pageno, @RequestParam(defaultValue = "10") int pagesize){
        return ResponseEntity.ok(service.findAll(pageno, pagesize));
    }

    //@RequestParam이나 @ModelAttribute에서는 사용자가 값을 넘기지 않으면 null이 된다
    //@Pathvariable은 사용자가 넘기는 주소 값이 주소의 일부
    // - pno가 111이라면 get.posts/111 값을 안 주면 get/posts 즉 주소가 달라진다
    
    @Operation(summary = "글읽기", description = "글읽기")
    @GetMapping("/posts/post")
    public ResponseEntity<Map<String,Object>> findByPno(@RequestParam int pno, Principal Principal){
        // 로그인했으면 로그인 아이디, 비로그인이면 null을 대입
        String loginId = Principal==null? null : Principal.getName();
        return ResponseEntity.ok(service.findByPno(pno,loginId));
    // MemberController : 컨트롤러에서 if문을 걸어서 200응답 또는 409 응답을 만든다
    // 또는 내가 원하는 않는 방향으로 진행되면 서비스에서 예외 발생 -> cotrollerAdvice로 넘긴다
    // controller는 200만 처리 (바람직한 방향만)
    }
}
