package com.gia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gia.domain.Message;
import com.gia.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;

	public Message findMessageById(long id) {
		return messageRepository.findOne(id);
	}

	public void updateMessage(long id, String content) {
		messageRepository.updateMessage(id, content);
	}

	public List<Message> findAll() {
		return messageRepository.findAll();
	}

}
