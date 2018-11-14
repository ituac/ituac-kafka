package com.ituac.kafka.module.euum;

/**
 * desp：kafa消息类型
 * @author ituac
 */

public enum MessageType {
	
	 KAFKA_DEFAULT("ituac-default","default"),
	 LOGS_SYSTEM("logs-system","system");
	 
    //防止字段值被修改，增加的字段也统一final表示常量
    private final String key;
    private final String value;
    
    private MessageType(String key,String value){
        this.key = key;
        this.value = value;
    }
    //根据key获取枚举
    public static MessageType getEnumByKey(String key){
        if(null == key){
            return null;
        }
        for(MessageType temp:MessageType.values()){
            if(temp.getKey().equals(key)){
                return temp;
            }
        }
        return null;
    }
    public String getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }

}
