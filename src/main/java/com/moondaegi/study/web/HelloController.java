package com.moondaegi.study.web;

import com.moondaegi.study.web.dto.HelloResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("hello/dto")
    public HelloResponseDTO helloResponseDTO(
            @RequestParam("name") String name,
            @RequestParam("amount") int amount) {
        return new HelloResponseDTO(name, amount);
    }
}
