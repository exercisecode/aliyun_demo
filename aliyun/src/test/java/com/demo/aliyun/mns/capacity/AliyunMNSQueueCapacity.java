package com.demo.aliyun.mns.capacity;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aliyun.mns.model.Message;
import com.demo.aliyun.AliyunDemoMain;
import com.demo.aliyun.mns.AliyunMNSQueueService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AliyunDemoMain.class)
public class AliyunMNSQueueCapacity {

	@Autowired
	private AliyunMNSQueueService aliyunMNSQueueService;

	@Test
	public void testCapacity() {

		String queueName = "lidongCapacityTest";
		aliyunMNSQueueService.deleteQueue(queueName);

		// create queue
		aliyunMNSQueueService.createQueue(queueName);

		int messageCount = 20000;

		// send message
		long sendStartTimeMillis = System.currentTimeMillis();
		for (int sendCount = 1; sendCount <= messageCount; sendCount++) {
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
			String message = "测试性能消息" + timeStamp;
			aliyunMNSQueueService.sendQueueMessage(queueName, message);
		}
		long sendEndTimeMillis = System.currentTimeMillis();

		// pop message
		long popStartTimeMillis = System.currentTimeMillis();
		int popMessageCount = 0;
		//Message popMessage = aliyunMNSQueueService.popQueueMessage(queueName);
		// MessageLog.log(queueName, popMessage);
		//while (popMessage != null) {
		//	aliyunMNSQueueService.deleteQueueMessage(queueName, popMessage.getReceiptHandle());
		//	popMessageCount++;
		//	popMessage = aliyunMNSQueueService.popQueueMessage(queueName);
		//	// MessageLog.log(queueName, popMessage);
		//}
		for(int popCount = 1; popCount <= messageCount; popCount++){
			Message popMessage = aliyunMNSQueueService.popQueueMessage(queueName);
			if(popMessage != null){
				
				aliyunMNSQueueService.deleteQueueMessage(queueName, popMessage.getReceiptHandle());
			}
		}
		long popEndTimeMillis = System.currentTimeMillis();

		long sendUsedTimeMillis = sendEndTimeMillis - sendStartTimeMillis;
		long popUsedTimeMillis = popEndTimeMillis - popStartTimeMillis;
		System.out.println(" \n\n\n send message count: " + messageCount + ";  sendUsedTimeMillis: "
				+ sendUsedTimeMillis + "; sendUsedSeconds: " + (sendUsedTimeMillis / 1000) + "\n\n");

		System.out.println(" \n\n\n pop message count: " + popMessageCount + "; popUsedTimeMillis: " + popUsedTimeMillis
				+ "; popUsedSeconds: " + (popUsedTimeMillis / 1000) + "\n\n");

	}

}
