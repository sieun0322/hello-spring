package com.example.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

/**
 * 여러 어플리케이션 실행
 * 1. 인텔리제 configuration
 * 	-Dserver.port=9002
 * 2. 인텔리제 Terminal
 *    mvn spring-boot:run -Dspring-boot.run.jvmArguments='-Dserver.port=9003'
 * 3. iterm
 * 	mvn clean
 * 	mvn compile package
 * 	java -jar -Dserver.port=9004 ./target/user-service-0.0.1-SNAPSHOT.jar
 *
 * 포트 0으로 설정시,
 * mvn spring-boot:run
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

}
