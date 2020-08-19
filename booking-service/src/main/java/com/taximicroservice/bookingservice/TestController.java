package com.taximicroservice.bookingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/test")
public class TestController {

    @Value("${msg:Config Server is not working. Please check...}")
    private String msg;

    @Autowired
    public void setEnv(Environment e) {
        System.out.println(e.getProperty("msg"));
    }

    @GetMapping
    public String getMsg() {
        return this.msg;
    }

}
