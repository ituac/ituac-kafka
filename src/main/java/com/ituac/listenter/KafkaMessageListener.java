package com.ituac.listenter;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;
import com.ituac.kafka.handle.KafkaMessageHandle;
import lombok.extern.log4j.Log4j2;

/**
 * desp: kafka监听器
 * @author ituac
 */

@Log4j2
@Component
public class KafkaMessageListener{
	
	
	private final KafkaMessageHandle kafkaMessageHandle;
	
	/**
     * 默认线程池大小 设置为1是为了调试方便；实际使用时应该配置为池的优化大小
     */
    private static Integer CORE_THREAD_NUM = 10;
 
    /**
     * 定义线程处理器的线程池
     */
    private ExecutorService threadPool = Executors.newFixedThreadPool(getDefaultThreadNum());
 
    @Autowired
    public KafkaMessageListener(KafkaMessageHandle kafkaMessageHandle){
        this.kafkaMessageHandle = kafkaMessageHandle;
    }
    
    
    /**
     * 默认配置用与 cpu核数 相同个数的线程
     * 
     * @return
     */
    protected int getDefaultThreadNum() {
        int cpuNum = Runtime.getRuntime().availableProcessors();
        if(cpuNum > 0) {
            return cpuNum;
        } else {
            return CORE_THREAD_NUM;
        }
    }
    
    
    
    /*********************************以下进行监听各个主题消息************************************/
    
	
    /*
     * 监听ituac-users-oper主题，进行多业务处理
     */
    @KafkaListener(topics = {"ituac-users-oper"})
    public void listenUsersOper(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {

            String message = (String) kafkaMessage.get();
            log.info("kafka |开始接收消息|topic:{}|patition:{}|offset:{}",record.topic(),record.partition(),record.offset());
    		threadPool.execute(new Runnable() {
    			@Override
    			public void run() {
    				try {
    					kafkaMessageHandle.usersOperHandle(record.topic(),message);
    				} catch (Exception e) {
    					log.error("kafka | 消息处理失败 |message:{}|e:{}",message,e);
    				}
    				log.info("kafka | 消息处理成功 |message:{}",message);
    			}
    		});
        }
    }
    
	
}
