package com.iambishal.spring_boot_rest_api_boilerplate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class SpringBootRestApiBoilerplateApplicationTests {

	@Test
	void contextLoads() {
	}

}
