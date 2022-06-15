package com.surenusi.springbootposts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringbootpostsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootpostsApplication.class, args);
	}

}
