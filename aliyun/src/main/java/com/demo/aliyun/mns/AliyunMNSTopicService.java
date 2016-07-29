package com.demo.aliyun.mns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Base64TopicMessage;
import com.aliyun.mns.model.SubscriptionMeta;
import com.aliyun.mns.model.TopicMessage;
import com.aliyun.mns.model.TopicMeta;

@Service
public class AliyunMNSTopicService {

	static Logger log = LoggerFactory.getLogger(AliyunMNSTopicService.class);

	@Autowired
	private MNSClient mnsClient;

	public CloudTopic createTopic(String topicName) {
		CloudTopic cloudTopic = null;
		try {
			TopicMeta topicMeta = new TopicMeta();
			topicMeta.setTopicName(topicName);

			cloudTopic = mnsClient.createTopic(topicMeta);
			if (cloudTopic != null) {
				//log.info("-----createTopic topicName:{} success. ", topicName);

			} else {
				log.warn("-----createTopic topicName:{} fail. ", topicName);
			}

		} catch (Exception e) {
			MNSExceptionHandler.deal(e);
		}
		return cloudTopic;
	}

	/**
	 * 
	 * 
	 * @param topicName
	 * @param subscriptionName
	 * @param queueEndpoint
	 *            queueEndpoint有三种形式
	 *            <p>
	 *            1. 以 http://为前缀; <br/>
	 *            2. QueueEndpoint
	 *            acs:mns:{REGION}:{AccountId}:queues/{QueueName}; <br/>
	 *            3. MailEndpoint mail:directmail:{MailAddress} <br/>
	 *            </p>
	 * @return
	 */
	public String subscribeTopic(String topicName, String subscriptionName, String subscriptionEndpoint) {
		String subscriptionUrl = null;
		try {
			CloudTopic cloudTopic = mnsClient.getTopicRef(topicName);
			if (cloudTopic != null) {

				SubscriptionMeta subscriptionMeta = new SubscriptionMeta();
				subscriptionMeta.setSubscriptionName(subscriptionName);

				subscriptionMeta.setEndpoint(subscriptionEndpoint);
				subscriptionMeta.setNotifyContentFormat(SubscriptionMeta.NotifyContentFormat.SIMPLIFIED);

				subscriptionUrl = cloudTopic.subscribe(subscriptionMeta);
				//log.info("-----subscribeTopic subscriptionUrl:{} ", subscriptionUrl);

			} else {
				log.warn("-----subscribeTopic topicName:{} not exists. ", topicName);
			}

		} catch (Exception e) {
			MNSExceptionHandler.deal(e);
		}
		return subscriptionUrl;
	}

	public TopicMessage sendTopicMessage(String topicName, String message) {
		TopicMessage sendMessage = null;
		try {
			CloudTopic cloudTopic = mnsClient.getTopicRef(topicName);
			if (cloudTopic != null) {
				TopicMessage topicMessage = new Base64TopicMessage();
				topicMessage.setMessageBody(message);
				sendMessage = cloudTopic.publishMessage(topicMessage);
				//log.info("-----sendTopicMessage topicName:{} afterSendMessage:{} ", topicName, sendMessage);
			} else {
				log.warn("-----sendTopicMessage topicName:{} not exists. ", topicName);
			}
		} catch (Exception e) {
			MNSExceptionHandler.deal(e);
		}
		return sendMessage;
	}

	public void unsubscribeTopic(String topicName, String subscriptionName) {
		try {
			CloudTopic cloudTopic = mnsClient.getTopicRef(topicName);
			if (cloudTopic != null) {
				cloudTopic.unsubscribe(subscriptionName);
				//log.info("-----unsubscribeTopic topicName:{} success. ", topicName);
			} else {
				log.warn("-----unsubscribeTopic topicName:{} not exists. ", topicName);
			}
		} catch (Exception e) {
			MNSExceptionHandler.deal(e);
		}
	}

	public void deleteTopic(String topicName) {
		try {
			CloudTopic cloudTopic = mnsClient.getTopicRef(topicName);

			if (cloudTopic != null) {
				cloudTopic.delete();
				//log.info("-----deleteTopic topicName:{} success. ", topicName);
			} else {
				log.warn("-----deleteTopic topicName:{} not exists. ", topicName);
			}

		} catch (Exception e) {
			MNSExceptionHandler.deal(e);
		}
	}

}
