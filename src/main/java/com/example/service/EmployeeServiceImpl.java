package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.EmployeeDao;
import com.example.model.employee.EmployeeVO;

import reactor.core.publisher.Flux;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeDao;

	@Override
	public Flux<EmployeeVO> getEmploye() {

		return employeDao.getEmploye();
	}

	@Override
	public void saveEmployee(List<EmployeeVO> employeeVO) {
		
		employeDao.saveEmployee(employeeVO);
	}

	@Override
	public void updateEmployee(int index,EmployeeVO employee) {
		employeDao.updateEmployee(index,employee);

	}

	@Override
	public void deleteEmployee(int index,int employeId) {

		employeDao.deleteEmployee(index,employeId);
	}

	@Override
	public void clearEmployee() {
		employeDao.clearEmployee();

	}

}
