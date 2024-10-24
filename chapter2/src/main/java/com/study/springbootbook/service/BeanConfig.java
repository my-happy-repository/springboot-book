package com.study.springbootbook.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public BeanTest beanTest() {
        return new BeanTest();
    }
}
