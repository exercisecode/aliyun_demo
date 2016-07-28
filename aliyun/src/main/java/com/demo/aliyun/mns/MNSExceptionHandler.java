package com.demo.aliyun.mns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;

public class MNSExceptionHandler {

	static Logger log = LoggerFactory.getLogger(MNSExceptionHandler.class);

	public static void deal(Exception e) {

		if (ClientException.class.equals(e.getClass())) {
			ClientException clientException = (ClientException) e;
			log.error("-----Network connection exception. ClientException :{} ", clientException);
		} else if (ServiceException.class.equals(e.getClass())) {
			ServiceException serviceException = (ServiceException) e;
			if ("QueueNotExist".equals(serviceException.getErrorCode())) {
				log.error("-----Queue is not exists. ServiceException:{} ", serviceException);
			} else if ("TimeExpired".equals(serviceException.getErrorCode())) {
				log.error("-----The request is time expired. ServiceException:{} ", serviceException);
			}

		} else {
			log.error("-----Exception:{} ", e);
		}
	}

}
