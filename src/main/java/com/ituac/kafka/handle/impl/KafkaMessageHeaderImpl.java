package com.ituac.kafka.handle.impl;

import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ituac.kafka.handle.KafkaMessageHandle;
import com.ituac.kafka.module.KafkaMessage;
import com.ituac.kafka.module.KafkaMessageHeader;
import com.ituac.pojo.po.TestUsersOper;
import lombok.extern.log4j.Log4j2;

/**
 * desp: kafka处理消息
 * @author ituac
 *
 */

@Log4j2
@Service
public class KafkaMessageHeaderImpl implements KafkaMessageHandle{
	

	
	@Override
	public void usersOperHandle(String topic, String message) {
		log.info("kafka | kafkaHandle | 处理类接收消息 | {}", JSONObject.toJSONString(message));
		KafkaMessage kafkaMessage = JSONObject.parseObject(message, KafkaMessage.class);
	    KafkaMessageHeader header = kafkaMessage.getMessageHeader();
	    String messageBody = kafkaMessage.getJsonString();
	    switch (header.getMessageType()) {
	        case "default":
	        	TestUsersOper usersOper = ((JSON) JSONObject.parse(messageBody)).toJavaObject(TestUsersOper.class);
	        	log.info(usersOper.toString()); 
	            break;
	            
	        case "system":
	            System.out.println("请在此处添加自定义的业务逻辑，处理system中的messageBody...");
	            break;
	
	        default:
	            log.info("没有对应的消息类型匹配, message={}", message);
	    }
	}


}
