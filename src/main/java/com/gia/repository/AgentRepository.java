package com.gia.repository;

import com.gia.domain.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * Author: Fenglin
 * Date: 2017/7/12
 */
public interface AgentRepository extends JpaRepository<Agent, Long>, JpaSpecificationExecutor<Agent> {

    /**
     * 空字符串搜索全部
     * null搜索不出结果
     * 普通字符串匹配
     * @param group
     * @return
     */
    @Query(value = "from Agent a where a.group like %:group% ")
    List<Agent> findAgentByGroup(@Param("group") String group);


    /**
     * 空集合会报错；null搜不出任何结果
     * @param ages
     * @return
     */
    @Query(value = "from Agent a where a.age in :ages")
    List<Agent> findAgentByAge(@Param("ages")Set<Integer> ages);
}
