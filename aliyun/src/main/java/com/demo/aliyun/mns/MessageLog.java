package com.demo.aliyun.mns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.mns.model.Message;

public class MessageLog {

	static Logger log = LoggerFactory.getLogger(MessageLog.class);

	public static void log(String queueName, Message message) {
		if (message != null) {
			log.info("-----log queueName:{} message:{} ", queueName, message);
		} else {
			log.info("-----log queueName:{} message is null ", queueName);
		}
	}

}
