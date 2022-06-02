package com.xiangliban.managementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration      //加入配置
@EnableSwagger2     //开启Swagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xiangliban.managementsystem.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Archett0", "https://github.com/Archett0", "mickeylyc@outlook.com");

        return new ApiInfo(
                "乡里办后台接口文档",
                "乡里办乡村基层治理系统API",
                "v1.0.0",
                "http://localhost:8080",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }
}
