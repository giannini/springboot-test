package com.gia;

import com.gia.cache.AgentNotFoundException;
import com.gia.cache.GuavaCacheServiceImpl;
import com.gia.domain.Agent;
import com.gia.domain.User;
import com.gia.repository.AgentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gia.service.MessageService;
import com.gia.service.UserService;

import javax.sound.midi.Soundbank;
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
    public void testCache() throws  Exception {
        try {
            System.out.println(AgentNotFoundException.class.getName());
            long id = cacheService.get("1827acef-127cfde");

            System.out.println(id);

            System.out.println(cacheService.get("18272ef-127cfde"));
        }catch (ExecutionException e) {
            System.out.println(e.getMessage());
            System.out.println("catch AgentNotFoundException.");
        }

    }


}
