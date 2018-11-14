package com.ituac.kafka.module;

import java.io.Serializable;
import lombok.Data;

/**
 * kafka消息结构
 * @author ituac
 */

@Data
public class KafkaMessage implements Serializable,Cloneable {

	private static final long serialVersionUID = 2179120946029850154L;
	
	/*
	 * 消息头
	 */
	protected KafkaMessageHeader messageHeader;
	
	/*
     * JSON格式 消息体
     */
    private String jsonString;
    
    
}
