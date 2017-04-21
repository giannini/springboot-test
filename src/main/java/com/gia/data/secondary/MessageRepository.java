package com.gia.data.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MessageRepository extends JpaRepository<Message, Long> {


	@Transactional(value = "transactionManagerSecondary")
	@Modifying
	@Query("update Message m set m.name=:name where m.id=:id")
	public int updateMessageName(@Param("id") Long id, @Param("name") String name);

	@Transactional(value = "transactionManagerSecondary")
	@Modifying
	@Query("update Message m set m.content=:content where m.name=:name")
	public int updateMessagebyName(@Param("name") String userName, @Param("content") String content);

}
