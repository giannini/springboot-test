package com.gia.domain;

import javax.persistence.*;

/**
 * Created by Fenglin on 2017/5/13.
 */
@Entity
@Table(name = "agent")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "node_id")
    private String nodeId;

    @Column(name = "ip")
    private String ip;

    public Agent(long id, String nodeId, String ip) {
        this.id = id;
        this.nodeId = nodeId;
        this.ip = ip;
    }

    public Agent(String nodeId, String ip) {
        this.nodeId = nodeId;
        this.ip = ip;
    }

    public Agent() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}

