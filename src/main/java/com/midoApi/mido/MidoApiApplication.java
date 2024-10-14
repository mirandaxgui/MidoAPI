package com.midoApi.mido;

import com.midoApi.mido.controllers.ClientesController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.midoApi.mido.repositories")
@EntityScan("com.midoApi.mido.models")
public class MidoApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MidoApiApplication.class, args);
	}


}
