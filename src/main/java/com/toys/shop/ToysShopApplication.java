package com.toys.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.toys.shop.Repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ComponentScan({ "com.toys.shop.*" })
@SpringBootApplication
public class ToysShopApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ToysShopApplication.class, args);
		ProductRepository productRepository = context.getBean(ProductRepository.class);

	}

	
}
