package com.wds.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		PersonWebClient pwc= new PersonWebClient();
		System.out.println(pwc.getResult());
	}

}
