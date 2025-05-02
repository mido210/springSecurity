package com.example.demo6.util;

import lombok.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.*;
import org.springframework.security.web.access.*;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.authentication.logout.*;

@EnableMethodSecurity(securedEnabled = true)
@Configuration
// final로 선언한 필드를 대상으로 하는 생성자를 만들어준다 - 스프링에서 생성자를 이용해 객체를 주입할 때 사용
@RequiredArgsConstructor
public class SecurityConfig {
    //401 오류 처리(로그인 필요하다) 인증
    private final AuthenticationEntryPoint authenticationEntryPoint;
    //403 오류 처리(권한 오류) 인가
    private final AccessDeniedHandler accessDeniedHandler;
    // 로그인 성공 200
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    // 로그인 실패 409
    private final AuthenticationFailureHandler authenticationFailureHandler;
    // 로그아웃 200
    private final LogoutSuccessHandler logoutSuccessHandler;



    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //스프링 시큐리티는 11개의 필터들의 집합체
    // 필터를 생성, 등록하는 설정 함수
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity config) throws Exception{
        // csrf: Mvc 방식에서 타임리프 파일을 위조 , 변조 하는 것을 막기 위해 사용
        config.csrf(csrf->csrf.disable());
        config.formLogin(form->form.loginPage("/login").loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler));
        config.logout(logout->logout.logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler));
        config.exceptionHandling(handler->
                handler.accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint));
        return config.build();
    }
}
