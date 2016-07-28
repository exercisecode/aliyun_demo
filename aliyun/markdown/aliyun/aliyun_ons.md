
# aliyun_ons.md  


# 阿里云 消息队列 ONS 文档  

- [ONS 产品介绍](https://help.aliyun.com/document_detail/29532.html)  
- [ONS Java SDK TCP](https://help.aliyun.com/document_detail/29546.html)  
- [ONS Java SDK HTTP](https://help.aliyun.com/document_detail/35186.html)  
- ONS Java SDK maven repo(未知)  
- [ONS Java SDK mvnrepository](http://mvnrepository.com/artifact/com.aliyun.openservices/ons-client)  



# Java工程配置  


## TCP接入  

https://help.aliyun.com/document_detail/29546.html  


```  

<!-- https://mvnrepository.com/artifact/com.aliyun.mns/aliyun-sdk-mns -->
<dependency>
    <groupId>com.aliyun.mns</groupId>
    <artifactId>aliyun-sdk-mns</artifactId>
    <version>1.1.5</version>
</dependency>


```  

## HTTP接入  

https://help.aliyun.com/document_detail/35186.html  
需要添加HTTP Java依赖  

```  
<!-- https://mvnrepository.com/artifact/org.eclipse.jetty/jetty-client -->
<dependency>
    <groupId>org.eclipse.jetty</groupId>
    <artifactId>jetty-client</artifactId>
    <version>9.4.0.M0</version>
</dependency>

<!-- https://mvnrepository.com/artifact/com.aliyun.openservices/ons-client -->
<dependency>
    <groupId>com.aliyun.openservices</groupId>
    <artifactId>ons-client</artifactId>
    <version>1.2.4</version>
</dependency>


官网使用 jetty-client 9.3.4.RC1 和 ons-client 1.1.11  


```  




# 消息队列  ONS 计费说明  

消息队列 详细价格信息

消息队列（Message Queue，简称MQ）是阿里云商用的专业消息中间件，是企业级互联网架构的核心产品，基于高可用分布式集群技术，搭建了包括发布订阅、轨迹查询、资源统计、定时（延时）、监控报警等一套完整的消息云服务。MQ历史超过7年，帮您实现分布式计算场景中所有异步解耦功能，是阿里双11使用的核心产品。MQ由阿里巴巴集团中间件技术部自主研发，是原汁原味的阿里集团中间件技术精华之沉淀，是性价比最高、最可靠的企业级消息中间件产品。


**每月前2000万次API调用免费，所有Region累计计算。** 
HTTP接入（简单、免费）TCP接入（专业）MQTT接入（移动、物联、免费/支持SSL加密、websocket、flash）  
3种消息发送方式，消息场景全覆盖：同步可靠、异步可靠、oneway方式  
公网测试Region，不收取Topic资源占用费，测试Region在免费调用次数范围内依然费用为0。  
MQ总费用=API调用费用+Topic资源占用费用。  



计费说明  

1、MQ产品为后付费产品，每自然日出一次账单，自动从账户余额扣除。  
2、MQ产品还有部分功能在免费期，具体项目请参考文档中心中免费项目说明。免费项目不适用于本产品的服务等级协议。  
3、调用发送接收API均计费，计量单位为百万次；API调用次数包括长轮询时API的调用，正常收发消息时，不会产生多余长轮询API调用。  
4、Topic资源占用费按每个Topic每日API调用次数阶梯计费，主-主账号授权双方均正常计费，主-子账号授权所有计费归属主账号。  
5、普通消息最大限制为 4 MB。每 64 KB发布或订阅数据以 1 次请求计费。例如，1 次负载（发布或订阅）为 256 KB 的 API 调用将以 4 次请求计费。  
6、事务消息发布请求一次按照50次计费，订阅按照普通消息计费。例如，发布事务消息一次，订阅该消息一次，按照50+1=51次API调用计费。  
7、定时消息发布请求一次按照25次计费，订阅按照普通消息计费。例如，发布定时消息一次，订阅该消息一次，按照25+1=26次API调用计费。  
8、金融云服务计费参考金融云不同region的计费系数。  
9、欠费说明  



