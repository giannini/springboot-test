package com.gia.repository;

import com.gia.domain.SofaClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Fenglin on 2017/5/25.
 */
public interface SofaClientRepository extends JpaRepository<SofaClient, Long> {

    @Query(value = "from SofaClient c where c.createTime>:time group by c.from, c.to")
    List<SofaClient> querySofaClientGroupbyApp(@Param("time") Date time);

}
