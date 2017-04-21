package com.gia;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gia.data.primary.UserRepository;
import com.gia.factory.Factory;
import com.gia.service.MessageService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
public class ApplicationTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MessageService msgService;

	@Autowired
	private Factory fac;

	@Test
	public void test() throws Exception {

		// userRepository.save(new User("aaa", 10));
		// userRepository.save(new User("bbb", 20));
		// userRepository.save(new User("ccc", 30));
		// userRepository.save(new User("ddd", 40));
		// userRepository.save(new User("eee", 50));
		//
		// Assert.assertEquals(5, userRepository.findAll().size());
		//
		// messageRepository.save(new Message("o1", "aaaaaaaaaa"));
		// messageRepository.save(new Message("o2", "bbbbbbbbbb"));
		// messageRepository.save(new Message("o3", "cccccccccc"));
		//
		// Assert.assertEquals(3, messageRepository.findAll().size());

		// List<Message> allMsgs = msgService.getAllMessages();
		// for (Message msg : allMsgs) {
		// System.out.println(msg.getId() + ":" + msg.getName() + "," +
		// msg.getContent());
		// }
		//
		// int ret = msgService.updateMessageContentByName("balabala2", "o2");
		// System.out.println("update o2: " + ret);
		//
		// ret = msgService.updateMessageNameById(14L, "rose");
		// System.out.println("update id(14L): " + ret);
		
	}

	@Test
	public void testConfig()
	{
		System.out.println(fac.getSwitch());
	}


}
