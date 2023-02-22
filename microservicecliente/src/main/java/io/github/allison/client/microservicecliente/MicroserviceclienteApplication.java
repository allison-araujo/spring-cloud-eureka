package io.github.allison.client.microservicecliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviceclienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceclienteApplication.class, args);
	}

}
