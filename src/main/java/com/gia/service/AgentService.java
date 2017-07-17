package com.gia.service;

import com.gia.domain.Agent;
import com.gia.domain.AgentSearchDTO;
import com.gia.repository.AgentRepository;
import org.apache.commons.lang3.StringUtils;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Author: Fenglin
 * Date: 2017/7/12
 */
@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;


    /**
     * @param agentSearchDTO
     * @return
     */
    public List<Agent> queryAgent(AgentSearchDTO agentSearchDTO) {
        Sort sort = new Sort(Sort.Direction.DESC, "gmtCreate");
        Specification<Agent> specification = getWhereClause(agentSearchDTO);
        Page<Agent> all = agentRepository.findAll(specification,
                new PageRequest(agentSearchDTO.getPage() - 1, agentSearchDTO.getLimit(), sort));
        return all.getContent();
    }

    @Transactional
    public void insert(Agent agent) {
        agentRepository.save(agent);
    }

    public List<Agent> find(String group) {
        return agentRepository.findAgentByGroup(group);
    }

    public List<Agent> find(Set<Integer> ages) {
        return agentRepository.findAgentByAge(ages);
    }

    public List<Agent> find(List<Integer> ages) {
        return agentRepository.findByAgeIn(ages);
    }

    /**
     * 动态生成where语句
     *
     * @param agentSearchDTO
     * @return
     */
    private Specification<Agent> getWhereClause(final AgentSearchDTO agentSearchDTO) {
        return new Specification<Agent>() {
            public Predicate toPredicate(Root<Agent> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicate = new ArrayList<Predicate>();
                // tag equal
                if (StringUtils.isNotBlank(agentSearchDTO.getTag())) {
                    predicate.add(criteriaBuilder.equal(root.get("tag").as(String.class), agentSearchDTO.getTag()));
                }
                // name like
                if (StringUtils.isNotBlank(agentSearchDTO.getName())) {
                    predicate.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + agentSearchDTO.getName() + "%"));
                }
                // group equal
                if (StringUtils.isNotBlank(agentSearchDTO.getGroup())) {
                    predicate.add(criteriaBuilder.equal(root.get("group").as(String.class), agentSearchDTO.getGroup()));
                }
                // age greater
                if (agentSearchDTO.getAge() != null) {
                    predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("age").as(Integer.class), agentSearchDTO.getAge()));
                }
                // create time between
                if (agentSearchDTO.getCreateTimeStart() != null) {
                    predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("gmtCreate").as(Timestamp.class),
                            agentSearchDTO.getCreateTimeStart()));
                }
                if (agentSearchDTO.getCreateTimeEnd() != null) {
                    predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("gmtCreate").as(Timestamp.class),
                            agentSearchDTO.getCreateTimeEnd()));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return criteriaQuery.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }
}
