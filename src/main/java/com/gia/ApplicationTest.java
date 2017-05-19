package com.gia;

import com.alibaba.fastjson.JSON;
import com.gia.cache.GuavaCacheServiceImpl;
import com.gia.domain.Agent;
import com.gia.repository.AgentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gia.service.MessageService;
import com.gia.service.UserService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
public class ApplicationTest {

    @Autowired
    private MessageService msgService;

    @Autowired
    private UserService userService;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private GuavaCacheServiceImpl cacheService;

    @Test
    public void test() throws Exception {
        String name = "QiQi";
        int age = 30;
        String content = "123456789012345678901234567890";

        userService.insertUserAndMessage(name, age, content);

    }

    @Test
    public void addAgent() {

        Agent a1 = new Agent("1827acef-127cfde", "123.56.234.67");
        Agent a2 = new Agent("87364ace-567eac", "74.123.26.123");

        agentRepository.save(a1);
        agentRepository.save(a2);


    }


    @Test
    public void testCache() throws Exception {
        try {
            long id = cacheService.get("1827acef-127cfde");

            System.out.println(id);

            System.out.println(cacheService.get("18272ef-127cfde"));
        } catch (ExecutionException e) {
            System.out.println(e.getMessage());
            System.out.println("catch AgentNotFoundException.");
        }

    }

    @Test
    public void testList() {

        List<Long> ids = agentRepository.getAgentIdList(5);
        System.out.println(JSON.toJSONString(ids));
    }


    @Test
    public void queryByDateTest() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -5);
        Date time = cal.getTime();
        System.out.println(time);
        List<Long> ids = agentRepository.getAgentByDate(5, time);
        System.out.println(JSON.toJSONString(ids));
    }

}
