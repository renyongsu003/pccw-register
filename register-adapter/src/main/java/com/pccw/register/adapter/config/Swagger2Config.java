package com.pccw.register.adapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {


    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).enable(true).select()
            .apis(RequestHandlerSelectors.basePackage("com.pccw.register.adapter")).paths(PathSelectors.any())
            .build().apiInfo(new ApiInfoBuilder().title("注册服务").description("注册服务").version("1.0.0")
                .contact(new Contact("renyongsu", "", "renyongsu@sohu.com")).build());
    }

}
