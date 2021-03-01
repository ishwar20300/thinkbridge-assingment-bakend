package com.shopbridge;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages = {"com.shopbridge"})
@EntityScan( basePackages = {"com.shopbridge"} )
@SpringBootApplication
@EnableScheduling
public class ShopBridgeApplication {

	public static void main(String[] args) {
		System.setProperty("server.port","8088");
		System.setProperty("file.encoding", "UTF-8");
		SpringApplication.run(ShopBridgeApplication.class, args);
	}
}
