package com.demo.aliyun.mns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.QueueMeta;

@Service
public class AliyunMNSQueueService {

	static Logger log = LoggerFactory.getLogger(AliyunMNSQueueService.class);

	@Autowired
	private MNSClient mnsClient;

	public CloudQueue createQueue(String queueName) {
		CloudQueue cloudQueue = null;
		try {
			QueueMeta queueMeta = new QueueMeta();
			queueMeta.setQueueName(queueName);
			queueMeta.setPollingWaitSeconds(0);
			queueMeta.setMaxMessageSize(1024 * 10L);

			cloudQueue = mnsClient.createQueue(queueMeta);
			if (cloudQueue != null) {
				log.info("-----createQueue queueName:{} success. CloudQuery:{} ", queueName, cloudQueue);
			} else {
				log.warn("-----createQueue queueName:{} fail. ", queueName);
			}

		} catch (Exception e) {
			MNSExceptionHandler.deal(e);
		}
		return cloudQueue;

	}

	public String sendQueueMessage(String queueName, String message) {
		String messageId = null;
		try {
			CloudQueue cloudQueue = mnsClient.getQueueRef(queueName);
			if (cloudQueue != null) {
				Message sendMessage = new Message();
				sendMessage.setMessageBody(message);

				Message afterSendMessage = cloudQueue.putMessage(sendMessage);
				if (afterSendMessage != null) {
					messageId = afterSendMessage.getMessageId();
				}
				log.info("-----sendQueueMessage after send messageId:{} afterSendMessage:{} ", messageId,
						afterSendMessage);
			} else {
				log.warn("-----sendQueueMessage getQueueRef queueName:{} return null. ", queueName);
			}
		} catch (Exception e) {
			MNSExceptionHandler.deal(e);
		}

		return messageId;
	}

	public Message popQueueMessage(String queueName) {
		Message popMessage = null;
		try {
			CloudQueue cloudQueue = mnsClient.getQueueRef(queueName);
			popMessage = cloudQueue.popMessage();
			if (popMessage != null) {
				log.info(
						"-----popQueueMessage queueName:{} messageId:{} messageReceipHandle:{} messageDequeueCount:{} messageBody:{} ",
						queueName, popMessage.getMessageId(), popMessage.getReceiptHandle(),
						popMessage.getDequeueCount(), popMessage.getMessageBodyAsString());
			} else {
				log.info("-----popQueueMessage queueName:{} no message. ", queueName);
			}
		} catch (Exception e) {
			MNSExceptionHandler.deal(e);
		}
		return popMessage;
	}

	public void deleteQueueMessage(String queueName, String receiptHandle) {
		try {
			CloudQueue cloudQueue = mnsClient.getQueueRef(queueName);
			if (cloudQueue != null) {
				cloudQueue.deleteMessage(receiptHandle);
				log.info("-----deleteQueueMessage queueName:{} deleteMessage receiptHandle:{} ", queueName,
						receiptHandle);
			} else {
				log.info("-----deleteQueueMessage queueName:{} not exists ", queueName);
			}
		} catch (Exception e) {
			MNSExceptionHandler.deal(e);
		}
	}

	public void deleteQueue(String queueName) {
		try {
			CloudQueue cloudQueue = mnsClient.getQueueRef(queueName);
			if (cloudQueue != null) {
				cloudQueue.delete();
				log.info("-----deleteQueue queueName:{}  ", queueName);
			} else {
				log.info("-----deleteQueue queueName:{} not exists ", queueName);
			}

		} catch (Exception e) {
			MNSExceptionHandler.deal(e);
		}
	}

}
