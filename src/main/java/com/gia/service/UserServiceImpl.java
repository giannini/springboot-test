package com.gia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gia.domain.Message;
import com.gia.domain.User;
import com.gia.repository.MessageRepository;
import com.gia.repository.UserRepository;

@Service
@EnableTransactionManagement
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageRepository messageRepository;

	public User getUserById(long id) {
		return userRepository.getOne(id);
	}

	public void updateUser(String name, int age) {
		userRepository.updateUser(name, age);
	}

	@Transactional
	public void insertUserAndMessage(String name, int age, String content) {
		User user = new User(name, age);
		Message message = new Message(content, name);
		userRepository.save(user);
		messageRepository.save(message);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

}
