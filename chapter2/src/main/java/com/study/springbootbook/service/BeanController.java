package com.study.springbootbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BeanController {

    private final BeanTest beanTest;

    @Autowired
    public BeanController(BeanTest beanTest) {
        this.beanTest = beanTest;
    }
}
