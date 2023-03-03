package io.github.allison.cloud.microservicescloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class MicroservicescloudgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicescloudgatewayApplication.class, args);
	}
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder){
		return builder
				.routes()
				.route(r -> r.path("/client/**").uri("lb://msclient") )
				.route(r -> r.path("/cards/**").uri("lb://mscards"))
				.route(r -> r.path("/avaliable-credit/**").uri("lb://msavaliablecredit"))

				.build();
	}

}
