package com.gia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ServletComponentScan
@RestController
public class App 
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
		SpringApplication.run(App.class, args);
    }

//	@RequestMapping("/world")
//	@ResponseBody
//	@PreAuthorize("hasRole('ADMIN')")
//	public String world() {
//		return "world";
//	}
}
