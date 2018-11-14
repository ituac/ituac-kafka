package com.ituac.kafka.handle;

/**
 * desp: kafja消息处理接口
 * @author ituac
 */

public interface KafkaMessageHandle {

	
	/**
	 * desp: 接收消息
	 * @param topic 主题
	 * @param message 消息
	 */
	void usersOperHandle(String topic, String message);

}
