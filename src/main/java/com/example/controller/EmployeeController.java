package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.model.employee.EmployeeVO;
import com.example.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	@GetMapping("/hello")
	public String  hello(){
		return "hello";	
	}
	
	@GetMapping("/employeelist")
	public Flux<EmployeeVO>  getEmploye(){
		return employeeService.getEmploye();	
	}
	
	@PostMapping("/saveemployee")
	public void saveEmploye(@RequestBody List<EmployeeVO> employeeVO){
		employeeService.saveEmployee(employeeVO);	
	}
	
	
	@PutMapping("/updateEmployee/{index}")
	public void updateEmployee(@RequestBody EmployeeVO employee,@PathVariable("index") int index){
		employeeService.updateEmployee(index, employee);	
	}
	
	@DeleteMapping("/deleteEmployee/{index}")
	public void deleteEmployee(@RequestParam("employeeId") int employeeId,@PathVariable("index") int index){
		employeeService.deleteEmployee(index, employeeId);	
	}
	
	@GetMapping("/clearEmployee")
	public void clearEmployee(){
		employeeService.clearEmployee();	
	}
	
	@GetMapping("/employeewebclient")
	public Flux<EmployeeVO> EmployeeWebClient() {
		
		WebClient client = WebClient.create("http://localhost:8085");

		 Flux<EmployeeVO> employeeFlux = client.get()
		      .uri("/employeelist").accept(MediaType.APPLICATION_JSON)
		      .retrieve().bodyToFlux(EmployeeVO.class);		  
		    return employeeFlux;
		
	}
}
