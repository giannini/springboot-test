package com.gia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Fenglin
 * Date: 2017/7/6
 */
@RestController
@RequestMapping(value = "/api/v1")
public class Hello1Controller {

    @Autowired

    @RequestMapping(value = "/hello")
    public String hello2() {
        return  "welcome to world 1.";
    }
}
