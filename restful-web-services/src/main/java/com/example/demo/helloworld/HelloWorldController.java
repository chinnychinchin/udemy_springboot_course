package com.example.demo.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping("/hello-world-bean/pathvariable/{name}")
    public HelloWorldBean helloWorldPathVariable (@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello %s",name));
    }

    @GetMapping("/hello-world-bean/params")
    public HelloWorldBean helloWorldParams (@RequestParam(name="welcomeMsg",defaultValue = "Welcome to Singapore") String welcomeMsg) {
        return new HelloWorldBean(String.format("Hello World, %s",welcomeMsg));
    }

}
