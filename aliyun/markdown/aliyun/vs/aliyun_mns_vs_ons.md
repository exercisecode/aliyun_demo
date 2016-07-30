

# aliyun_mns_vs_one.md 阿里云 MNS 和 ONS 对比  

消息服务 MNS  与 消息队列 ONS 对比  

<table>
	<tr>
		<th> 对比项目 </th>    <th> 消息服务 MNS </th>    <th> 消息队列 ONS </th>
	</tr>	

	<tr>
		<td> 发展历史 </td>
		<td> 2012/07立项MQS; 2015/07改名MNS </td>
		<td> 内部产品名 MetaQ, Notify; 开源社区名 RocketMQ </td>
	</tr>	

	<tr>
		<td> 基础模型 </td>    <td> 队列模型Queue, 主题订阅模型Topic </td>    <td> 消息主题Topic, Producer ID, Consumer ID </td>
	</tr>	
	<tr>
		<td> 模型特性 </td>    <td> Queue 和 Topic 属于两个不同模型 </td>    <td> Producer ID 与 Topic 关系为 1:N.  Consumer ID 与 Topic 关系为 N:N. </td>
	</tr>	
	
	<tr>
		<td> 队列特点 </td>
		<td> 丰富的队列属性, 支持并发访问, 消息投递保障, 分布式事务支持 </td>
		<td rowspan="2"> 专业, 高可靠, 高性能, 多协议接入, 独立部署 </td>  
	</tr>	
	<tr>
		<td> 主题订阅特点 </td>
		<td> 支持通知消息, 支持一对多广播消息, 支持多种投递方式, 消息投递保障, 消息标签过滤 </td>
	</tr>	


	<tr>
		<td> 队列模型 </td>    
		<td> 生产者, 消费者, 普通队列, 延时队列, Endpoint, 普通消息, 延时消息, 消息ID(Message ID), 临时句柄(ReceiptHandle), 消息状态 </td>
		<td> 一个Topic 仅包含 一个 Producer ID 和 一个 Consumer ID组成队列</td>

	</tr>	
	<tr>
		<td> 主题订阅模型 </td>
		<td> 发布者Publisher, 订阅者Subscriber, 订阅Subscription, 推送地址Endpoint,  TopicUrl, Message. 支持三种订阅: HTTP, QueueEndpoint, MailEndpoint </td>
		<td> 一个 Topic 被多个 Consumer ID 订阅 </td>
	</tr>	
	<tr>
		<td> 名词解释 </td>
		<td> (上两个分别解释) </td>
		<td> Message Queue, Message, Message Id, Message Key, Topic, Tag, Producer, Producer ID, Producer实例, Consumer,  Consumer Id, Consumer实例, 集群消费, 广播消费, 定时消费, 延时消费, 定时消息, 延时消息, 消息堆积, 消息过滤, 消息轨迹, 重置消费位点 </td>
	</tr>	


	<tr>
		<td> 队列消息状态 </td>    
		<td> 活跃消息(Active), 延迟消息(Delay), 非活跃消息(Inctive) </td>    
		<td> NOT ONLINE 订阅断不在线, CONSUMED 消息已被投递, CONSUMED_BUT_FILTERED 消息已被投递且被过滤, NOT_CONSUME_YET 消息未被投递 </td>
	</tr>	

	<tr>
		<td> 消息存放时间 </td>
		<td> Queue 消息的最长存活时间由创建队列时指定的 MessageRetentionPeriod 属性值决定 <br/><br/> Topic 消息发布到 Topic 后，最长存活时间是 1天 </td>
		<td> ONS 默认消息持久化存储 3 天，支持重置消费位点消费3天之内任何时间点的消息 </td>
	</tr>	

	<tr>
		<td> 可靠性 </td>
		<td> 高达99.99999999% </td>
		<td>  </td>
	</tr>	
	<tr>
		<td> 服务可用性 </td>
		<td> 高达99.9% </td>
		<td>  </td>
	</tr>	
	<tr>
		<td> 消息上限 </td>
		<td> 单个队列的QPS上限为4000 </td>
		<td> 单Topic上限为5000条, 最高可申请10W以上 </td>
	</tr>	
	<tr>
		<td> 消息大小 </td>
		<td> 64KB, 大消息可以借助OSS </td>
		<td> 默认最大支持256KB, 华北2支持4MB大消息 </td>
	</tr>	
	<tr>
		<td> 消息堆积 </td>
		<td> 单个队列中积压的消息数量不得超过10亿条 </td>
		<td> 单个 Topic 可堆积 100亿+条消息，系统高流量压力下依然可靠。 </td>
	</tr>	

</table>  

