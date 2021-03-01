package com.shopbridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/*
import org.springframework.boot.web.support.SpringBootServletInitializer;*/
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.shopbridge"})
@EntityScan( basePackages = {"com.shopbridge"} )
@SpringBootApplication
public class ShopBridgeServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ShopBridgeServletInitializer.class);
	}

	public static void main(String[] args) throws Exception {
		System.setProperty("file.encoding", "UTF-8");
		SpringApplication.run(ShopBridgeServletInitializer.class, args);
	}

}