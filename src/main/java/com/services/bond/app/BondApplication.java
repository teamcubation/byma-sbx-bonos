package com.services.bond.app;

import com.services.bond.app.configuration.ConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class BondApplication implements CommandLineRunner {

	private final ConfigService configService;

	public static void main(String[] args) {
		SpringApplication.run(BondApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Application Name: {}", configService.getApplicationName());
	}
}
