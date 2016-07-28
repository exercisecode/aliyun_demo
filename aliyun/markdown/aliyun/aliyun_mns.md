
# aliyun_mns.md  


# 阿里云 消息服务 MNS 文档  

- [MNS 产品介绍](https://help.aliyun.com/document_detail/27414.html)    
- [MNS Java SDK](https://help.aliyun.com/document_detail/27508.html)  
- [MNS Java SDK maven repo](https://repo1.maven.org/maven2/com/aliyun/mns/aliyun-sdk-mns/)  
- [MNS Java SDK mvnrepository](http://mvnrepository.com/artifact/com.aliyun.mns/aliyun-sdk-mns)  


# Java工程配置

```  
<!-- https://mvnrepository.com/artifact/com.aliyun.mns/aliyun-sdk-mns -->
<dependency>
    <groupId>com.aliyun.mns</groupId>
    <artifactId>aliyun-sdk-mns</artifactId>
    <version>1.1.5</version>
</dependency>

```  

# MNS 价格  

https://help.aliyun.com/document_detail/27422.html 

## 1. 费用构成  

` 请求次数 + 堆积消息费用 + 外网下行流量费用 `  

## 2. 请求次数费用  

①请求次数: MNS对每个计量周期内的 API 请求次数进行计量，以百万次为单位，精确到小数点后三位，第三位根据第四位四舍五入。  

②请求次数单价： 2.00元/百万次  

## 3. 堆积消息费用  

如果您所有队列中的消息数量超过100万个，MNS 将对超出部分计费；  

堆积消息费用计算公式如下： 堆积消息费用＝超出100万部分的消息总数 X 堆积消息单价  

①求次数: MNS 对每个计量时点堆积消息超出100万的部分进行计量，以百万为单位，精确到小数点后三位，第三位根据第四位四舍五入。  

②堆积消息单价： 0.010元/百万条·小时  


## 4. 外网下行流量费用

MNS 对消息数据从 MNS 传出至 Internet 或阿里云其他 Region 的总流量进行计费；（MNS的内网域名只建议在该region内部使用，免收流量费）

**外网下行流量费用计算公式如下： 外网下行流量费用＝外网下行流量 X 外网下行流量单价**

①网下行流量: MNS 对每个计量周期内的外网下行流量进行计量，以 GB 为单位，精确到小数点后两位，第二位根据第三位四舍五入。

②外网下行流量单价： 0.80元/GB

## 5. 付费周期和停服机制

## 6. 免费额度   

**每个用户每月有100万次请求的免费额度。**  

注：本免费额度与套餐包优惠无法叠加（即：当选择包年包月优惠套餐包之后，在套餐包有效期间将不再享受每月100万次的免费额度）。  


