package com.banco.cuenta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceCuentaAhorroApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCuentaAhorroApplication.class, args);
	}

}
