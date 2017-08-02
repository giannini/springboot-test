package com.gia.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@RestController
public class App {

    public static void main( String[] args ) {
		SpringApplication.run(App.class, args);
    }

	@RequestMapping("/")
	@ResponseBody
	public String hello() {
		return "hello";
	}

	@Value("${foo}")
	String foo;

	@RequestMapping(value = "/hi")
	public String hi(){
		return foo;
	}
}