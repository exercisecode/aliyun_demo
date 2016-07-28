package com.demo.aliyun.mns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.QueueMeta;

@Service
public class AliyunMNSQueueService {
	
	static Logger log = LoggerFactory.getLogger(AliyunMNSQueueService.class);

	private static String QUEUE_NAME = "lidongxuDemoQuery";

	@Autowired
	private MNSClient mnsClient;

	public void createQueue() {
		try {
			QueueMeta queueMeta = new QueueMeta();
			queueMeta.setQueueName(QUEUE_NAME);
			queueMeta.setPollingWaitSeconds(15);
			queueMeta.setMaxMessageSize(1024 * 10L);

			CloudQueue queue = mnsClient.createQueue(queueMeta);
			log.info("-----createQuery name:{} CloudQuery:{} ", QUEUE_NAME, queue);
			
		} catch (Exception e) {
			MNSExceptionHandler.deal(e);
		}

	}

}
