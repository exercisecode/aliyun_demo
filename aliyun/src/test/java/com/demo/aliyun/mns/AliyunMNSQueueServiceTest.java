package com.demo.aliyun.mns;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.aliyun.AliyunDemoMain;

import junit.framework.TestCase;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AliyunDemoMain.class)
public class AliyunMNSQueueServiceTest extends TestCase {
	
	@Autowired
	private AliyunMNSQueueService aliyunMNSQueueService;
	
	@Test
	public void testCreateQuery(){
		aliyunMNSQueueService.createQueue();
	}

}
