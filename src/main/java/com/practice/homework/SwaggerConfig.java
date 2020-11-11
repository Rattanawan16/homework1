package com.practice.homework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket produceApi() {
        return new Docket(DocumentationType.SWAGGER_12).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.practice.homework.controllers")).paths(paths()).build();
    }

    // Describe your apis
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Homework Rest APIs")
                .description("This page lists all the rest apis.")
                .version("1.0-SNAPSHOT")
                .build();

    }

    // Only select apis that matches the given Predicates.
    private Predicate<String> paths() {
// Match all paths except /error
        return Predicates.and(PathSelectors.any(), Predicates.not(PathSelectors.regex("/error.*")));
    }


/*
    @Bean
    public Docket api() {
        ApiSelectorBuilder apiSelectorBuilder = new Docket(DocumentationType.SWAGGER_2)
                .groupName("api")
                .select();

        apiSelectorBuilder.apis(RequestHandlerSelectors.basePackage("com.practice.homework.controllers"));
        apiSelectorBuilder.paths(regex("/api.*"));

        return apiSelectorBuilder.build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Homework Rest APIs")
                .description("This page lists all the rest apis.")
                .version("1.0-SNAPSHOT")
                .build();
    }

//    public Docket produceApi() {
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
//                .apis(RequestHandlerSelectors.basePackage("com.practice.homework.controllers")).paths(regex("/produceApi.*")).build();
////                .apis(RequestHandlerSelectors.basePackage("com.practice.homework.controllers")).paths(paths()).build();
//    }
//
//    // Describe your apis
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Homework Rest APIs")
//                .description("This page lists all the rest apis.")
//                .version("1.0-SNAPSHOT")
//                .build();
//
//    }

    // Only select apis that matches the given Predicates.
//    private Predicate<String> paths() {
//// Match all paths except /error
//        return Predicates.and(PathSelectors.any(), Predicates.not(PathSelectors.regex("/error.*")));
//    }
*/
}
