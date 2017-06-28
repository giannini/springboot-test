package com.gia.repository;

import com.gia.domain.po.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Fenglin on 2017/6/28.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findById(long id);
}
