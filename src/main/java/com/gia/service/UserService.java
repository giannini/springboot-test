package com.gia.service;

import java.util.List;

import com.gia.domain.User;

public interface UserService {

	User getUserById(long id);

	void updateUser(String name, int age);

	void insertUserAndMessage(String name, int age, String content);

	List<User> findAll();
}
