package com.gia.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Fenglin on 2017/5/25.
 */
@Entity
@Table(name = "t_sofa_client")
public class SofaClient {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "fromapp")
    private String from;

    @Column(name = "toapp")
    private String to;

    @Column(name = "invokecnt")
    private  String invokeCnt;

    @Column(name = "createtime", updatable = false)
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getInvokeCnt() {
        return invokeCnt;
    }

    public void setInvokeCnt(String invokeCnt) {
        this.invokeCnt = invokeCnt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
