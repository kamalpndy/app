package com.example.model.employee;

public class EmployeeVO {
	
	private int employeId;
	private String employeName;
	private String employeAddress;
	
	 public EmployeeVO() {
		 
	 }
	 public EmployeeVO(String employeName, String employeAddress, int employeId) {
	        this.employeName = employeName;
	        this.employeAddress = employeAddress;
	        this.employeId = employeId;
	    }

	public int getEmployeId() {
		return employeId;
	}

	public void setEmployeId(int employeId) {
		this.employeId = employeId;
	}

	public String getEmployeName() {
		return employeName;
	}

	public void setEmployeName(String employeName) {
		this.employeName = employeName;
	}

	public String getEmployeAddress() {
		return employeAddress;
	}

	public void setEmployeAddress(String employeAddress) {
		this.employeAddress = employeAddress;
	}

}
