package com.gia.jdbctemplate.service;

import java.util.List;

import com.gia.jdbctemplate.dao.User;

public interface UserService {

	public void create(String name, int age);

	public void deleteByName(String name);

	public Integer countUsers();

	public List<User> getAllUsers();

	public User queryUserByName(String name);

}
