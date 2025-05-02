package com.example.demo6.advice;

import com.example.demo6.exception.*;
import jakarta.validation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

public class ProcessingFaultAdvice {
    //500: 처리 중 오류.  다양한 이유로 발생


    // 검증 실패에 대한 예외처리
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> ConstraintViolationException(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("잘못된 형식");
    }

    // 사용자 정의 : 객체가 없을 때 예외 처리
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> EntityNotFoundException(EntityNotFoundException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
