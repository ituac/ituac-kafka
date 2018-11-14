package com.ituac.kafka.module;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * desp: kafka消息头
 * @author ituac
 *
 */

@Data
public class KafkaMessageHeader implements Serializable,Cloneable{


	private static final long serialVersionUID = 6572523668410210186L;
	
	//消息惟一标识
    private String serialNo;
 
    //特殊系统关键业务单号
    private String systemSourceId;
 
    //创建时间
    private Date createTime;
 
    //消息类型，用于区分不同业务消息
    private String messageType;
    
    
}
