package com.gia.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Fenglin on 2017/5/13.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
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

    @Column(name = "create_time")
    @CreatedDate
    private Date createTime;

    @Column(name = "modify_time")
    @LastModifiedDate
    private Date modifyTime;

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

