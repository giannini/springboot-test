package com.gia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: Fenglin
 * Date: 2017/7/6
 */
@Service
public class Hello1Service {

    @Autowired
    private Hello1Service hello1Service;

    public  String hello1() {
        return  hello1Service.hello1();
    }
}
