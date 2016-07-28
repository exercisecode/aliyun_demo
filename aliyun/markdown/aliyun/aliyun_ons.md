
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





