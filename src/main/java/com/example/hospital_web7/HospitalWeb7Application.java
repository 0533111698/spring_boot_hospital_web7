package com.example.hospital_web7;

import com.example.hospital_web7.beans.Patient;
import com.example.hospital_web7.exeption.ExeptionDoctor;
import com.example.hospital_web7.facade.DPFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.influx.InfluxDbOkHttpClientBuilderProvider;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.function.Predicate;

@SpringBootApplication
public class HospitalWeb7Application {

	public static void main(String[] args) {
		SpringApplication.run(HospitalWeb7Application.class, args);


	}
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.paths(Predicate.not(PathSelectors.regex("/error.*")))
				.build();
	}

}
