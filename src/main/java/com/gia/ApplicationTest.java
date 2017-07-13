package com.gia;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gia.domain.Agent;
import com.gia.domain.AgentSearchDTO;
import com.gia.domain.SofaClient;
import com.gia.domain.User;
import com.gia.repository.SofaClientRepository;
import com.gia.service.AgentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gia.service.MessageService;
import com.gia.service.UserService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
public class ApplicationTest {

    @Autowired
    private MessageService msgService;

    @Autowired
    private UserService userService;


    @Autowired
    private SofaClientRepository sofaClientRepository;

    @Autowired
    private AgentService agentService;


    @Test
    public void test() throws Exception {
        String name = "Joba";
        int age = 30;
        String content = "123";

        userService.insertUserAndMessage(name, age, content);

    }

    @Test
    public void initData() throws Exception {
        Agent a = new Agent();
        a.setName("aaa");
        a.setTag("tagbbb");
        a.setGroup("groupccc");
        a.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        agentService.insert(a);
    }


    @Test
    public void testClient() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(cal.YEAR), cal.get(cal.MONTH), cal.get(cal.DATE), 0, 0, 0);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println(cal.getTime());


        List<SofaClient> sofaClients = sofaClientRepository.querySofaClientGroupbyApp(cal.getTime());
        System.out.println("sofaClients size: " + sofaClients.size());
        for (SofaClient client : sofaClients) {
            System.out.println(JSON.toJSONString(client, SerializerFeature.SortField));
        }

    }

    @Test
    public void testCriteriaQuery() throws ParseException {
        AgentSearchDTO agentSearch = new AgentSearchDTO();
        //agentSearch.setTag("tagbbb");
        // agentSearch.setName("a");
        //agentSearch.setAge(20);
        //agentSearch.setGroup("groupccc");
        String start = "2017-07-12 18:01:00";
        String end = "2017-07-12 18:02:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        agentSearch.setCreateTimeEnd(new Timestamp(sdf.parse(start).getTime()));
        agentSearch.setCreateTimeEnd(new Timestamp(sdf.parse(end).getTime()));

        List<Agent> agents = agentService.queryAgent(agentSearch);
        System.out.println(JSON.toJSONString(agents, SerializerFeature.SortField));
    }

    @Test
    public void testQueryClause() {
        String group = null;
        List<Agent> agents = agentService.find(group);
        System.out.println(JSON.toJSONString(agents));
    }

    @Test
    public void testQueryIn() {
        Set<Integer> ages =null;
        List<Agent> agents = agentService.find(ages);
        System.out.println(JSON.toJSONString(agents));

    }

}
