package com.gia;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gia.jdbctemplate.dao.User;
import com.gia.jdbctemplate.service.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
public class ApplicationTest {

	@Autowired
	private UserServiceImpl userSrv;

	@Test
	public void test() {

		// userSrv.create("Conan", 8);
		// userSrv.create("ccc", 29);
		// System.out.println("user count: " + userSrv.countUsers());

		// userSrv.deleteByName("ccc");
		// System.out.println("user count: " + userSrv.countUsers());

		for (User user : userSrv.getAllUsers()) {
			System.out.println("id:" + user.getId() + "name:" + user.getName() + ", age:" + user.getAge());
		}

		System.out.println(userSrv.queryUserByName("aaa").toString());
	}



}
