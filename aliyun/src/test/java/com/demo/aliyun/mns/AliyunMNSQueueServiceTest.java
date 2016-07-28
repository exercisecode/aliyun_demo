package com.demo.aliyun.mns;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aliyun.mns.model.Message;
import com.demo.aliyun.AliyunDemoMain;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AliyunDemoMain.class)
public class AliyunMNSQueueServiceTest extends TestCase {

	public static String QUEUE_NAME = "lidongxuDemoQuery";

	@Autowired
	private AliyunMNSQueueService aliyunMNSQueueService;

	@Test
	public void testFull() {
		// create queue
		aliyunMNSQueueService.createQueue(QUEUE_NAME);

		// send message
		// com.aliyun.mns.common.ServiceException: The queue name you provided
		// is not exist
		// aliyunMNSQueueService.sendQueueMessage("demo", "不存在的queue");
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
		aliyunMNSQueueService.sendQueueMessage(QUEUE_NAME, "lidongxu test数据信息" + timeStamp);

		List<String> receiptHandleList = new ArrayList<String>();
		// pop message
		Message popMessage = aliyunMNSQueueService.popQueueMessage(QUEUE_NAME);
		if (popMessage != null && popMessage.getReceiptHandle() != null) {
			receiptHandleList.add(popMessage.getReceiptHandle());
		}
		while (popMessage != null) {
			popMessage = aliyunMNSQueueService.popQueueMessage(QUEUE_NAME);
			if (popMessage != null && popMessage.getReceiptHandle() != null) {
				receiptHandleList.add(popMessage.getReceiptHandle());
			}
		}

		// delete message
		for (String receiptHandle : receiptHandleList) {
			aliyunMNSQueueService.deleteQueueMessage(QUEUE_NAME, receiptHandle);
		}

		// delete queue
		aliyunMNSQueueService.deleteQueue(QUEUE_NAME);

	}

}
