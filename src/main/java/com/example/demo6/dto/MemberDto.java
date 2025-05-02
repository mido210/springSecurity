package com.example.demo6.dto;

import com.example.demo6.entity.*;
import com.fasterxml.jackson.annotation.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.*;

import java.time.*;

public class MemberDto {

    // 사용자로부터 입력받는 클래스, MyBatis 출력하는 클래스는 반드시 @Setter가 있어야한다
    // 스프링빈, 마이바티스 리턴 객체는 기본생성자가 있어야한다
    // 기본 생성자로 생성한 다음 setter로 값을 집어넣습니다
    // @AllArgsConstructor를 사용하지 않는다
    // @Getter 검증시에도 @setter가 필요하다
    @Data
    public static class UsernameCheck {
        @NotEmpty
        @Pattern(regexp = "^[a-z0-9]{6,10}$")
        private String username;
    }

    @Data
    public static class Create {

        // 아이디는 소문자와 숫자 6~10자 -> 문자열 패턴을 검증할 때 사용하는 기술: "정규식"
        //[a-z] : 소문자 한개, [0-9] : 숫자 1개
        //[a-z0-9] : 범위들을 나열하면 또는으로 연결 -> 소문자 또는 숫자 1한글자
        // [a-z0-9]{6,10} : 소문자 또는 숫자 6~10자(을 포함하는)
        //^[a-z0-9]{6,10}& :^는 시작한다는 뜻 , $ 끝난다는 의미

        @NotEmpty
        @Pattern(regexp = "^[a-z0-9]{6,10}$")
        private String username;
        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z0-9]{6,10}$")
        private String password;
        @Email
        @NotEmpty
        private String email;
        //파일을 업로드 하는 형식/ 자바에서 파일은  file객체 multipartfie
        private MultipartFile profile;

        public Member toEntity(String encodedPassword, String base64Image) {
            return Member.builder().username(username).password(encodedPassword).email(email).profile(base64Image).build();
        }
    }

    @Data
    public static class GeneratePassword {
        @NotEmpty
        @Pattern(regexp = "^[a-z0-9]{6,10}$")
        private String username;
        @Email
        @NotEmpty
        private String email;
    }

    @Data
    @AllArgsConstructor
    public static  class Read{
        private String username;
        private String email;
        private String profile;
        @JsonFormat(pattern = "yyyy년 MM월 dd일")
        private LocalDate joinDay;
        private long days;
        private Level level;
    }

    @Data
    public static class PasswordChange{
        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z0-9]{6,10}$")
        private String currentPassword;

        @NotEmpty
        @Pattern(regexp = "^[a-zA-Z0-9]{6,10}$")
        private String newPassword;


    }


    public static class Update {

    }


}
