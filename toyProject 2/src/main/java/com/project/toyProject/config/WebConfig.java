package com.project.toyProject.config;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files/**") // 웹 경로
                .addResourceLocations("file:/Users/jongsung/Desktop/study/files/profile/"); // 실제 경로

        registry.addResourceHandler("/postFiles/**")
                .addResourceLocations("file:/Users/jongsung/Desktop/study/files/post/");
    }

}