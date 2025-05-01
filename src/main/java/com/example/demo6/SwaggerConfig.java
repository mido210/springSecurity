package com.example.demo6;


import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.*;
import io.swagger.v3.oas.models.*;

@Configuration
public class SwaggerConfig {
    public OpenAPI openAPI(){
        //http://localhost:8080/swagger-ui/index.html
        Info info = new Info().title("최종").description("게시판 api 문서화").version(("1.0"));
        return new OpenAPI().components(new Components()).info(info);
    }
}
