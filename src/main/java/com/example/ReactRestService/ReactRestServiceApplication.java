package com.example.ReactRestService;



import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import com.example.model.employee.EmployeeVO;


@SpringBootApplication
@ComponentScan("com.example")
public class ReactRestServiceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ReactRestServiceApplication.class, args);
	 }
	
}
