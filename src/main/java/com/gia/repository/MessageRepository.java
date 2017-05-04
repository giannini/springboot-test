package com.gia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.gia.domain.Message;

@Component
public interface MessageRepository extends JpaRepository<Message, Long> {

	@Modifying
	@Query(value = "update Message m set m.content=:content where id=:id")
	void updateMessage(@Param("id") long id, @Param("content") String content);
}
