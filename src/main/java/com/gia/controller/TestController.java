package com.gia.controller;

import com.gia.kafka.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Fenglin
 * Date: 2017/7/12
 */
@RestController
public class TestController {

    @Autowired
    private Sender sender;

    private String topic = "page_visits";

    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return "hello";
    }


    @RequestMapping("/kafka")
    @ResponseBody
    public String kafka() {
        sender.send(topic, "spring-boot-kafka-test-msg");
        return "done";
    }
}
