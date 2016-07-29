package com.demo.aliyun.mns;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.model.Message;
import com.demo.aliyun.AliyunDemoMain;

import junit.framework.TestCase;

@SpringApplicationConfiguration(AliyunDemoMain.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AliyunMNSTopicServiceTest extends TestCase {

	private static final String TOPIC_NAME = "lidongxuDemoTopic";

	@Autowired
	private AliyunMNSTopicService aliyunMNSTopicService;

	@Autowired
	private AliyunMNSQueueService aliyunMNSQueueService;

	@Test
	public void testFull() {

		// create topic
		aliyunMNSTopicService.createTopic(TOPIC_NAME);

		// subscription
		String[] subscriberQueueNameArray = new String[] { "subscriberQueue1", "subscriberQueue2" };
		for (String subscriberQueueName : subscriberQueueNameArray) {
			// https://34325862.mns.cn-beijing.aliyuncs.com/topics/lidongxuDemoTopic/subscriptions/subscriberQueue1 
			

			CloudQueue subscriberQueue = aliyunMNSQueueService.createQueue(subscriberQueueName);
			
			String subscriptionEndpoint = "acs:mns:cn-beijing:34325862:queues/" + subscriberQueue.getAttributes().getQueueName();
			aliyunMNSTopicService.subscribeTopic(TOPIC_NAME, subscriberQueueName, subscriptionEndpoint);
		}

		// send message
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
		aliyunMNSTopicService.sendTopicMessage(TOPIC_NAME, "李东旭使用Topic测试消息" + timeStamp);

		try {
			// 2 秒后, 可以获取递送消息
			Thread.sleep(1000 * 2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// verify queue message
		for (String subscriberQueueName : subscriberQueueNameArray) {

			Message message = aliyunMNSQueueService.popQueueMessage(subscriberQueueName);
			MessageLog.log(subscriberQueueName, message);
			while (message != null) {
				message = aliyunMNSQueueService.popQueueMessage(subscriberQueueName);
				MessageLog.log(subscriberQueueName, message);
			}
		}

		// unsubscriber queue
		for (String subscriberQueueName : subscriberQueueNameArray) {
			aliyunMNSTopicService.unsubscribeTopic(TOPIC_NAME, subscriberQueueName);
		}

		// delete topic and queue
		for (String subscribeQueueName : subscriberQueueNameArray) {
			aliyunMNSQueueService.deleteQueue(subscribeQueueName);
		}

		aliyunMNSTopicService.deleteTopic(TOPIC_NAME);

	}

}
