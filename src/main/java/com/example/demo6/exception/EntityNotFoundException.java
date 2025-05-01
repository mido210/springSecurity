package com.example.demo6.exception;

import lombok.*;

// 자바의 예외
// Exception의 자식들은 : try ~catch
//  ↑ RuntimeException의 자식을 : 예외처리 선택(체크하지 않는 예외)
@AllArgsConstructor
@Getter
public class EntityNotFoundException extends RuntimeException{
    private String message;
}
