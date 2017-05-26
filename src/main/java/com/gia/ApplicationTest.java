package com.gia;

import com.alibaba.fastjson.JSON;
import com.gia.domain.SofaClient;
import com.gia.repository.SofaClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gia.service.MessageService;
import com.gia.service.UserService;

import java.util.Calendar;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
public class ApplicationTest {

    @Autowired
    private MessageService msgService;

    @Autowired
    private UserService userService;


    @Autowired
    private SofaClientRepository sofaClientRepository;

    @Test
    public void test() throws Exception {
        String name = "QiQi";
        int age = 30;
        String content = "123456789012345678901234567890";

        userService.insertUserAndMessage(name, age, content);

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
            System.out.println(JSON.toJSONString(client));
        }

    }

}
