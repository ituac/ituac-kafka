package com.ituac;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
public class KafkaApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("ituac-kafka|启动参数|{}|", args);
	}
	
}
