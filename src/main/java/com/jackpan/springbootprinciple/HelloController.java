package com.jackpan.springbootprinciple;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController操作类
 *
 * @author JackPan
 * @date 2022/05/15 09:28
 **/
@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
