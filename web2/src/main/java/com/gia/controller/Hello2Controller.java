package com.gia.controller;

import com.gia.Hello2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Fenglin
 * Date: 2017/7/6
 */
@RestController
@RequestMapping(value = "/api/v2")
public class Hello2Controller {

    @Autowired
    private Hello2Service hello2Service;

    @RequestMapping(value = "/hello")
    public String hello2() {
        return hello2Service.hello2();
    }

}
