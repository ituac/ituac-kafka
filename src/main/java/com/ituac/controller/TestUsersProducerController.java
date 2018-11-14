package com.ituac.controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ituac.kafka.util.KafkaSendUtil;
import com.ituac.pojo.po.TestUsersOper;

import lombok.extern.log4j.Log4j2;

/**
 * desp: 用户行为日志  -> kafka
 * @author ituac
 */

@Log4j2
@RestController
@RequestMapping("/users")
public class TestUsersProducerController {

	@RequestMapping("/kafka/send")
    public String send(TestUsersOper userOper){
		userOper.setId(UUID.randomUUID().toString().replaceAll("-",""));
		userOper.setOperationTime(new Date());
		KafkaSendUtil.send("ituac-log-test", userOper);
        return "success";
    }
	
}
