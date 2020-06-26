package com.example.controller;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.model.employee.EmployeeVO;
import com.example.ratelimiter.OptumApiRateLimiter;
import com.example.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
@RestController
@RequestMapping("/api/v1")
@Api(value = "Employee Management System", description = "Operations pertaining to employee in Employee Managemant")	
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	@Autowired
	OptumApiRateLimiter optumApiRateLimiter;
	
	@GetMapping("/helloworld")
	public String  hello(){
		return "helloworld";	
	}
	
	@ApiOperation(value = "View a list of available employees", response = List.class)

	
	@ApiResponses(value = {

	    @ApiResponse(code = 200, message = "Successfully retrieved list"),

	    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),

	    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),

	    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")

	})
	@GetMapping("/employeelist")
	public Flux<EmployeeVO>  getEmploye(){
		return employeeService.getEmploye();	
	}
	
	@ApiOperation(value = "Add an employee")
	@PostMapping("/saveemployee")
	public void saveEmploye(@ApiParam(value = "Employee object store in database table", required = true) @Valid @RequestBody List<EmployeeVO> employeeVO){
		employeeService.saveEmployee(employeeVO);	
	}
	
	@GetMapping("/addEmployee")
	public void addEmploye(){
	List<EmployeeVO> l = new ArrayList<EmployeeVO>();
			
		for(int i=0;i<1000;i++) {
			EmployeeVO v =  new EmployeeVO();
			v.setEmployeId(i);
			v.setEmployeName("Ashish "+i);
			int age = 32+i;
			v.setAge(String.valueOf(age));
			v.setAddress("D-100");
			 l.add(v);
		}
		employeeService.saveEmployee(l);
	}
	
	@ApiOperation(value = "Update an employee")
	@PutMapping("/updateEmployee/{index}")
	public void updateEmployee(
			@ApiParam(value = "Update employee object", required = true) @Valid  @RequestBody EmployeeVO employee,
			@ApiParam(value = "Employee Id to update employee object", required = true) @PathVariable("index") int index){
		employeeService.updateEmployee(index, employee);	
	}
	
	@ApiOperation(value = "Delete an employee")
	@DeleteMapping("/deleteEmployee/{index}")
	public void deleteEmployee(
			@ApiParam(value = "Employee Id to update employee object", required = true) @Valid @RequestParam("employeeId") int employeeId,
			@ApiParam(value = "Index to delete employee object", required = true) @PathVariable("index") int index){
		employeeService.deleteEmployee(index, employeeId);	
	}
	
	@ApiOperation(value = "clear an employee object")
	@GetMapping("/clearEmployee")
	public void clearEmployee(){
		employeeService.clearEmployee();	
	}
	
	@GetMapping("/employeewebclient")
	public Flux<EmployeeVO> EmployeeWebClient() {
		
		WebClient client = WebClient.create("http://localhost:8080");
		List<EmployeeVO> employeeVOList = employeeService.getEmploye().collectList().block();
		System.out.println(employeeVOList.size());
		long startTime = ZonedDateTime.now().getSecond();
		System.out.println(startTime);
		employeeVOList.parallelStream().parallel().forEach(i -> {
			 optumApiRateLimiter.waitForLock();
			client.get()
				      .uri("/api/v1/employeelist").accept(MediaType.APPLICATION_JSON)
				      .retrieve().bodyToFlux(EmployeeVO.class);	
			});
		
		    long elapsedTimeSeconds = ZonedDateTime.now().getSecond() - startTime;
			System.out.println("Time taken to update notification db: " + elapsedTimeSeconds);
		    return null;
		
	}
	
	@GetMapping("/helloexample")
	public Mono<String> helloClient() {
		
		WebClient client = WebClient.create("http://172.17.0.2:8085");

		 Mono<String> response = client.get()
		      .uri("/hello").exchange().block().bodyToMono(String.class);		  
		    return response;
		
	}
}
