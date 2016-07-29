
# aliyun_mns_capacity.md 阿里云 MNS 性能测试


# 测试报告  

<table>
	<tr>
		<th>测试名称</th>  <th>使用 毫秒数</th>  <th>使用秒数</th> <th>每秒处理消息</th>
	</tr>	

	<tr>
		<td>发送2W queue消息(带日志, 单线程)</td>  <td> 395030 </td>  <td> 395 </td>  <td>50.6</td>
	</tr>

	<tr>
		<td>收取2W queue消息 (带日志, 单线程, 实际 15350)</td>  <td> 388576 </td>  <td> 388 </td>  <td> 39.6 </td>
	</tr>	

	<tr>
		<td>发送 2W queue消息(无日志, 单线程)</td>  <td> 351346 </td>  <td> 351 </td>   <td> 57.0 </td>
	<tr>	
	<tr>
		<td>收取 2W queue消息(无日志, 单线程)</td>  <td> 457580 </td>   <td> 457 </td>  <td> 43.8 </td>
	</tr>	
</table>	

