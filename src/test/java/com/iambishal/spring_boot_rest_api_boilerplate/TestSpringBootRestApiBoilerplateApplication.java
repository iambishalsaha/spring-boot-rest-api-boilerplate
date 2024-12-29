package com.iambishal.spring_boot_rest_api_boilerplate;

import org.springframework.boot.SpringApplication;

public class TestSpringBootRestApiBoilerplateApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringBootRestApiBoilerplateApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
