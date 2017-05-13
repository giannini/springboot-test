package com.gia.cache;

import com.gia.domain.Agent;
import com.gia.repository.AgentRepository;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Fenglin on 2017/5/13.
 */
@Service
public class GuavaCacheServiceImpl {


    private LoadingCache<String, Long> nodeIdCache;

    @Autowired
    private AgentRepository agentRepository;

    public GuavaCacheServiceImpl() {
        nodeIdCache = CacheBuilder.newBuilder().maximumSize(2000).
                expireAfterAccess(30, TimeUnit.MINUTES).
                build(new CacheLoader<String, Long>() {
            public Long load(String s) throws Exception {
                Agent agent = agentRepository.findAgentByNodeId(s);
                if (agent == null) {
                    throw new AgentNotFoundException();
                } else {
                    return agent.getId();
                }
            }
        });

    }

    public Long get(String key) throws ExecutionException {
        Long id = null;
        try {
           id  =  nodeIdCache.get(key);
        } catch (ExecutionException e) {
            if (e.getMessage().equalsIgnoreCase(AgentNotFoundException.class.getName())) {
                System.out.println("catch AgentNotFoundException");
                return null;
            }
            System.out.println("catch ExecutionException");
            throw e;
        }

        return id;
    }

    public void put(String key, Long val) {
        nodeIdCache.put(key, val);
    }

    public void delete(String key) {
        nodeIdCache.invalidate(key);

    }

    public boolean exist(String key) {
        return nodeIdCache.getIfPresent(key) == null;
    }

    public Long putIfAbsent(String key, Long val) {
        synchronized (nodeIdCache) {
            return nodeIdCache.asMap().putIfAbsent(key, val);
        }
    }

}
