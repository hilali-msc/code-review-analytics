package ca.ets.da.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {
		System.out.println("Starting...");
		SpringApplication.run(Application.class, args);
	}

}
