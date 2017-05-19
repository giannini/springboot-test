package com.gia.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test")
public class TestController {


    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello(@RequestBody String params) {
        JSONObject obj = JSON.parseObject(params);
        return obj.toJSONString();
    }

    @RequestMapping(value = "/hello2")
    @ResponseBody
    public String hello2(@RequestParam(value = "user")String user, @RequestParam(value = "age")Integer age) {
        JSONObject obj = new JSONObject();
        obj.put("user", user);
        obj.put("age", age);
        return obj.toJSONString();
    }

}
