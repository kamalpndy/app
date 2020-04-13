package com.example.dao;

import java.util.List;


import com.example.model.employee.EmployeeVO;

import reactor.core.publisher.Flux;


public interface EmployeeDao {
    
	public Flux<EmployeeVO> getEmploye();
	
	public void saveEmployee(List<EmployeeVO> employeeVO);
	
	public void updateEmployee(int index,EmployeeVO employee);
	
	public void deleteEmployee(int index,int employeeId);
	
	public void clearEmployee();
	
}
