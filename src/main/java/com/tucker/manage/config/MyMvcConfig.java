package com.tucker.manage.config;

import com.tucker.manage.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/page-login.html").setViewName("page-login");
    }

    /*@Bean
    public WebMvcConfigurationSupport webMvcConfigurationSupport() {
        WebMvcConfigurationSupport support = new WebMvcConfigurationSupport() {
            @Override
            protected void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/logins").setViewName("page-login");
                registry.addViewController("page-login.html").setViewName("page-login");
            }
        };
        return support;
    }*/
}
