package com.example.pagerdutyagent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PagerdutyagentApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(PagerdutyagentApplication.class, args);
		context.getBean(Initilizer.class).init();
	}

}
