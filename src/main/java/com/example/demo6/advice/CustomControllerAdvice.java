package com.example.demo6.advice;

import com.example.demo6.exception.*;
import jakarta.validation.*;
import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.*;
import org.springframework.web.servlet.resource.*;

@RestControllerAdvice
public class CustomControllerAdvice {
    // 검증 실패에 대한 예외처리
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String>ConstraintViolationException(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("잘못된 형식");
    }

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
    // 400을 처리 pno=100처럼 같이 필수 파라미터가 있지만 지정하지 않는 상황(생략된 상황)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> MissingServletRequestParameterException(MissingServletRequestParameterException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

    // MethodArgumentTypeMismatchException처리 400 파라미터 값이 장
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

    // 사용자 정의 : 객체가 없을 때 예외 처리
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> EntityNotFoundException(EntityNotFoundException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
