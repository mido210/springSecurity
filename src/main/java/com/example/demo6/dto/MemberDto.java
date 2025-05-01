package com.example.demo6.dto;

import com.example.demo6.entity.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.*;

public class MemberDto {

 @Getter
 public static class UsernameCheck{
  @NotEmpty
  @Pattern(regexp = "^[a-z0-9]{6,10}$")
  private String username;
 }
    @Data
     public static class Create{

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
         public Member toEntity(String encodedPassword, String base64Image){
          return Member.builder().username(username).password(encodedPassword).email(email).profile(base64Image).build();
         }
     }
     @Data
     public static class GeneratePassword{
         @NotEmpty
         @Pattern(regexp = "^[a-z0-9]{6,10}$")
         private String username;
         @Email
         @NotEmpty
         private String email;
     }

     public static class Update{

     }

     public static class Read{

     }
}
