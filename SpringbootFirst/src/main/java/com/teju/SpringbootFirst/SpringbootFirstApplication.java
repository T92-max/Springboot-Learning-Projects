package com.teju.SpringbootFirst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootFirstApplication {

	public static void main(String[] args) {

		ApplicationContext context= SpringApplication.run(SpringbootFirstApplication.class, args);
		Simple sm=context.getBean(Simple.class);
		sm.code();


	}

}
