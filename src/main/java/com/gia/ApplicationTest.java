package com.gia;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gia.service.MessageService;
import com.gia.service.UserService;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
public class ApplicationTest {

	@Autowired
	private MessageService msgService;

	@Autowired
	private UserService userService;

	@Test
	public void test() throws Exception {
		String name = "QiQi";
		int age = 30;
		String content = "123456789012345678901234567890";

		userService.insertUserAndMessage(name, age, content);

	}

}
