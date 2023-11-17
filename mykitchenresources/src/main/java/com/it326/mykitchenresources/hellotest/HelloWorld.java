package com.it326.mykitchenresources.hellotest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @RequestMapping("/hello-world")
    public void SayHello() {
        System.out.println("Hello World!");
    }
}