package com.lureb.petclinicrecreated.petclinicweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.lureb.petclinicrecreated.petclinicweb",
		"com.lureb.petclinicrecreated.petclinicdata"
})
public class PetClinicRecreatedApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetClinicRecreatedApplication.class, args);
	}

}
