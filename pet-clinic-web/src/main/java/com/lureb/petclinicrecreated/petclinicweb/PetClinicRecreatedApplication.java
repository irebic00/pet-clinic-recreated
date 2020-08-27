package com.lureb.petclinicrecreated.petclinicweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.lureb.petclinicrecreated.petclinicweb",
		"com.lureb.petclinicrecreated.petclinicdata"
})
@EnableJpaRepositories("com.lureb.petclinicrecreated.petclinicdata")
@EntityScan("com.lureb.petclinicrecreated.petclinicdata")
public class PetClinicRecreatedApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetClinicRecreatedApplication.class, args);
	}

}
