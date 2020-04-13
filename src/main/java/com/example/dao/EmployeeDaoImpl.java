package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.model.employee.EmployeeVO;

import reactor.core.publisher.Flux;

@Repository
public class EmployeeDaoImpl implements  EmployeeDao {

	private static List<EmployeeVO> list;
	static {
	      list = new ArrayList<>();
	}

	@Override
	public Flux<EmployeeVO> getEmploye() {
		
		return Flux.fromIterable(list);
	}

	@Override
	public void saveEmployee(List<EmployeeVO> employeeVO) {
		     list.addAll(employeeVO);
	}

	@Override
	public void updateEmployee(int index,EmployeeVO employee) {
		EmployeeVO employeeToBeUpdated = list.get(index);
		if(employeeToBeUpdated.getEmployeId() == employee.getEmployeId()) {
			list.add(employee);
		}
	}

	@Override
	public void deleteEmployee(int index,int employeeId) {
		EmployeeVO employee = list.get(index);
		if(employee.getEmployeId() == employeeId) {
			list.remove(index);
		}
	}

	@Override
	public void clearEmployee() {
		list = new ArrayList<>();
	}

}
