package com.demo.aliyun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AliyunDemoMain {
	
	static Logger log = LoggerFactory.getLogger(AliyunDemoMain.class);

	public static void main(String[] args) {
		
		ConfigurableApplicationContext configContext = SpringApplication.run(AliyunDemoMain.class, args);
		
		log.info("-----Start with beanCount:{} \n\n\n", configContext.getBeanDefinitionCount());
		
		
		

	}

}
