package com.gia.service;

import java.util.List;

import com.gia.domain.Message;

public interface MessageService {

	Message findMessageById(long id);

	void updateMessage(long id, String content);

	List<Message> findAll();

	void save(Message message);

}
