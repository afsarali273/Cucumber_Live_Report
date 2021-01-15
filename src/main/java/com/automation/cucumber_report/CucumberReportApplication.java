package com.automation.cucumber_report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.automation.cucumber_report.model"} )
@EnableJpaRepositories(basePackages = {"com.automation.cucumber_report.repo"})
public class CucumberReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(CucumberReportApplication.class, args);
	}

}
