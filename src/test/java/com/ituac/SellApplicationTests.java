package com.ituac;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellApplicationTests {
	
	
	@Test
    public void test1() {
	      String name = "imooc";
	      String password = "123456" ;
	      log.debug("debug");
	      log.info("info");
	      log.warn("warn");
	      log.error("error");
	}

}
