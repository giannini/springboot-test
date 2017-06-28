package com.gia.repository;

import com.gia.domain.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Fenglin on 2017/6/28.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String username);
}
