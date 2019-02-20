package com.qh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * 在spring boot的文档中，告诉我们添加@EnableRedisHttpSession来开启spring session支持
 * 而@EnableRedisHttpSession这个注解是由spring-session-data-redis提供的，所以在pom.xml文件中添加：
 * <dependency>
 * <groupId>org.springframework.boot</groupId>
 * <artifactId>spring-boot-starter-redis</artifactId>
 * </dependency>
 * <dependency>
 * <groupId>org.springframework.session</groupId>
 * <artifactId>spring-session-data-redis</artifactId>
 * </dependency>
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class})
@ServletComponentScan
@ComponentScan(basePackages = {"com.qh.modules.activiti.org.activiti", "com.qh"})
public class FlowworkApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FlowworkApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(FlowworkApplication.class);
	}

}
