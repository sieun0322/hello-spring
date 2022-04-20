package com.example.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

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
 */
@SpringBootApplication
@EnableEurekaClient
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
