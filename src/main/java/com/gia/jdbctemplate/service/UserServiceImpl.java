package com.gia.jdbctemplate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gia.jdbctemplate.dao.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public void create(String name, int age) {
		String sql = "insert into user(name, age) values(?,?)";
		jdbcTemplate.update(sql, name, age);
	}

	@Transactional
	public void deleteByName(String name) {
		String sql = "delete from user where name=?";
		jdbcTemplate.update(sql, name);
	}

	public Integer countUsers() {
		return jdbcTemplate.queryForObject("select count(*) from user", Integer.class);
	}

	public List<User> getAllUsers() {
		return jdbcTemplate.query("select * from user", new UserRowMapper());
	}

	public User queryUserByName(String name) {
		return jdbcTemplate.queryForObject("select * from user where name=?", new Object[] { name },
				new UserRowMapper());
	}


}
