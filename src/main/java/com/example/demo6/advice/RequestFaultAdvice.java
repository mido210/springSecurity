package com.example.demo6.advice;

import org.springframework.http.*;
import org.springframework.web.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.*;
import org.springframework.web.servlet.resource.*;

@RestControllerAdvice
public class RequestFaultAdvice {

    // 400을 처리 pno=100처럼 같이 필수 파라미터가 있지만 지정하지 않는 상황(생략된 상황)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> MissingServletRequestParameterException(MissingServletRequestParameterException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

    // MethodArgumentTypeMismatchException처리 400 파라미터 값의 타입이 일치하지않음
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

    //401: 인증이 필요하다 -> 스프링 시큐리티


    //403: 권환 확인이 필요하다 (인가) -> 스프링 시큐리티


    //404 예외처리
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handleState404(NoResourceFoundException e){
        // No static resource posts/ [postaaa -> "no", "static", "posts/postaaa."]로 분리
        String[] message = e.getMessage().split(" "); // 공백 주의
        String url = message[message.length-1];
        url = url.substring(0, url.length()-1);
        //System.out.println(url);
        return  ResponseEntity.status(404).body("잘못된 주소:"+ url);
    }


    // 405: 잘못된 메소드
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleState405(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("잘못된 메소드");
    }


}
