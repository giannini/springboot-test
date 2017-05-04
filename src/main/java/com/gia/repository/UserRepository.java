package com.gia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.gia.domain.User;

@Component
public interface UserRepository extends JpaRepository<User, Long> {

	@Modifying
	@Query(value = "update User u set u.age=:age where u.name=:name")
	void updateUser(@Param("name") String name, @Param("age") int age);
}
