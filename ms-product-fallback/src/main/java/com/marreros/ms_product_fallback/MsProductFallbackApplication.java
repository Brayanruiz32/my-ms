package com.marreros.ms_product_fallback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsProductFallbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProductFallbackApplication.class, args);
	}

}
