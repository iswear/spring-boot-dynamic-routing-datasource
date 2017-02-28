package com.hy.dynamicdatasource;

import com.hy.dynamicdatasource.util.application.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
@MapperScan("com.hy.dynamicdatasource.mapper")
@EnableTransactionManagement
@ImportResource("classpath:/SpringContext-Transaction.xml")
public class YggPushOrderHzhsApplication {

	public static void main(String[] args) {
		SpringContextUtil.setApplicationContext(SpringApplication.run(YggPushOrderHzhsApplication.class, args));
	}

}
