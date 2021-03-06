package com.zsm.mini;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.zsm.mini.dao")
public class M2MiniApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(M2MiniApiApplication.class, args);
	}

}

