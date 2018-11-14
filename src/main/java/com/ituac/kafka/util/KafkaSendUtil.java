package com.ituac.kafka.util;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import com.alibaba.fastjson.JSONObject;
import com.ituac.kafka.module.KafkaMessage;
import com.ituac.kafka.module.KafkaMessageHeader;
import com.ituac.kafka.module.euum.MessageType;
import com.ituac.utils.spring.SpringUtils;

import lombok.extern.log4j.Log4j2;

/**
 * kafka发送器
 * @author itauc
 */

@Log4j2
public class KafkaSendUtil {

	/**
	 * 发送信息
	 * @param <T>
	 * @param topic 主题
	 * @param message 信息
	 */
	public static <T> void send(String topic, T value) {
		
		//设置消息
		KafkaMessage kafkaMessage = new KafkaMessage();
		//设置Kafka消息头
		KafkaMessageHeader mesageHeader = new KafkaMessageHeader();
		mesageHeader.setCreateTime(new Date());
		mesageHeader.setSerialNo(UUID.randomUUID().toString().replaceAll("-",""));
		mesageHeader.setMessageType(MessageType.KAFKA_DEFAULT.getValue());
		kafkaMessage.setMessageHeader(mesageHeader);
		//设置消息体
		kafkaMessage.setJsonString(JSONObject.toJSONString(value));
		//得到的消息
		String message = JSONObject.toJSONString(kafkaMessage);
		
		log.info("|kafka|{}|==>>>|{}|",topic,value);
		KafkaTemplate<String, String> kafkaTemplate = (KafkaTemplate<String, String>) SpringUtils.getBean("kafkaTemplate");
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
		sendCallBack(future);
	}
	
	
	/**
	 * 发送信息
	 * @param topic 指定 topic
	 * @param partition 指定分区
	 * @param value 数据
	 * @param key 指定key
	 */
    public static void send(String topic, int partition, String value,String key){
    	log.info("|kafka|{}|==>>>|{}|{}",topic,partition,value);
    	
    	//设置消息
		KafkaMessage kafkaMessage = new KafkaMessage();
		//设置Kafka消息头
		KafkaMessageHeader mesageHeader = new KafkaMessageHeader();
		mesageHeader.setCreateTime(new Date());
		mesageHeader.setSerialNo(UUID.randomUUID().toString().replaceAll("-",""));
		mesageHeader.setMessageType(MessageType.KAFKA_DEFAULT.getValue());
		kafkaMessage.setMessageHeader(mesageHeader);
		//设置消息体
		kafkaMessage.setJsonString(JSONObject.toJSONString(value));
		//得到的消息
		String message = JSONObject.toJSONString(kafkaMessage);
    	
		KafkaTemplate<String, String> kafkaTemplate = (KafkaTemplate<String, String>) SpringUtils.getBean("kafkaTemplate");
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, partition, key, message);
		sendCallBack(future);
    }
	
	/**
	 * kafka消息回调
	 * @param future
	 */
	private static void sendCallBack(ListenableFuture<SendResult<String, String>> future) {
		try {
			SendResult<String, String> sendResult = future.get();
			future.addCallback(SuccessCallback -> {
                log.info("kafka |{}|发送消息成功|partition:{}|offset:{}",sendResult.getRecordMetadata().topic(),sendResult.getRecordMetadata().partition(),sendResult.getRecordMetadata().offset());
            },
            FailureCallback->log.error("kafka|发送消息失败|sendResult:{}",JSONObject.toJSONString(sendResult.getProducerRecord())));
		} catch (Exception e) {
			log.error("kafka|返回值异常:{}",e);
		}
		
	}
	
}
