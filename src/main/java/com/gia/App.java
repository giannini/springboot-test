package com.gia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@RestController
@EnableScheduling
public class App {

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
		ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);
		// ctx.getBean(MessageRepository.class);
		// SpringApplication.run(App.class, args);
		System.out.println("App start up!");
    }

	@RequestMapping("/")
	@ResponseBody
	public String hello() {
		return "hello";
	}
}
