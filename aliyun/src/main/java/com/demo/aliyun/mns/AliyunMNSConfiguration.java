package com.demo.aliyun.mns;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.MNSClient;

@Configuration
public class AliyunMNSConfiguration {
	
	@Value("${aliyun.accessId}")
	private String aliyunAccessId;
	
	@Value("${aliyun.accessKey}")
	private String aliyunAccessKey;
	
	@Value("${aliyun.mnsEndpoint}")
	private String aliyunMNSEndpoint;
	
	@Bean
	public CloudAccount aliyunCloudAccount(){
		CloudAccount aliyunCloudAccount = new CloudAccount(aliyunAccessId, aliyunAccessKey, aliyunMNSEndpoint);
		return aliyunCloudAccount;
	}
	
	@Bean
	public MNSClient aliyunMNSClient(){
		MNSClient aliyunMNSClient = aliyunCloudAccount().getMNSClient();
		return aliyunMNSClient;
	}

}
