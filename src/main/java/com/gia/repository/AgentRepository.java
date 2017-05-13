package com.gia.repository;

import com.gia.domain.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Fenglin on 2017/5/13.
 */
public interface AgentRepository extends JpaRepository<Agent, Long> {

    @Query(value = "from Agent a where a.id=:id")
    Agent findAgent(@Param("id") long agentId);


    @Query(value = "from Agent a where a.nodeId=:node_id")
    Agent findAgentByNodeId(@Param("node_id") String nodeId);
}
