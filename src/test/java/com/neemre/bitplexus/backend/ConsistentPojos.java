package com.neemre.bitplexus.backend;

import com.neemre.bitplexus.backend.model.Employee;
import com.neemre.bitplexus.backend.model.EmployeeRole;

public class ConsistentPojos {
 
	public static void main(String[] args) {
		Employee employee1 = new Employee();
		employee1.setMemberId(1);
		employee1.setEmployeeId(1);
		Employee employee2 = new Employee();
		employee2.setMemberId(2);
		employee2.setEmployeeId(2);
		
		EmployeeRole employeeRole11 = new EmployeeRole();
		employeeRole11.setEmployeeRoleId(11);
		employeeRole11.setEmployee(employee1);
		System.out.println();
		
		EmployeeRole employeeRole12 = new EmployeeRole();
		employeeRole12.setEmployeeRoleId(12);
		employeeRole12.setEmployee(employee1);
		System.out.println();
		
		EmployeeRole employeeRole13 = new EmployeeRole();
		employeeRole13.setEmployeeRoleId(13);
		employeeRole13.setIsActive(false);
		employeeRole13.setEmployee(employee1);
		System.out.println();
		
		EmployeeRole employeeRole14 = new EmployeeRole();
		employeeRole14.setEmployeeRoleId(14);
		employeeRole14.setIsActive(false);
		employeeRole14.setEmployee(employee1);
		System.out.println();
		
		EmployeeRole employeeRole15 = new EmployeeRole();
		employeeRole15.setEmployeeRoleId(15);
		employeeRole15.setIsActive(true);
		employeeRole15.setEmployee(employee1);
		System.out.println();
		
		EmployeeRole employeeRole16 = new EmployeeRole();
		employeeRole16.setEmployeeRoleId(16);
		employeeRole16.setEmployee(employee1);
		System.out.println();
		
		EmployeeRole employeeRole17 = new EmployeeRole();
		employeeRole17.setEmployeeRoleId(17);
		employeeRole17.setIsActive(true);
		employeeRole17.setEmployee(employee1);
		System.out.println();
		
		EmployeeRole employeeRole18 = new EmployeeRole();
		employeeRole18.setEmployeeRoleId(18);
		employeeRole18.setEmployee(employee1);
		System.out.println();
		
		EmployeeRole employeeRole19 = new EmployeeRole();
		employeeRole19.setEmployeeRoleId(19);
		employeeRole19.setIsActive(false);
		employeeRole19.setEmployee(employee1);
		System.out.println();
		
		EmployeeRole employeeRole20 = new EmployeeRole();
		employeeRole20.setEmployeeRoleId(20);
		employeeRole20.setIsActive(true);
		employee1.addEmployeeRole(employeeRole20);
		System.out.println();
		
		employee2.addEmployeeRole(employeeRole20);
		System.out.println();
		
		employeeRole20.setEmployee(employee1);
		System.out.println();
		
		employee2.addEmployeeRole(null);
		System.out.println();
		
		employee1.removeEmployeeRole(employeeRole20);
		System.out.println();
		
		employee2.removeEmployeeRole(null);
		System.out.println();
		
		
		System.out.printf("employee1: \"%s\";\n", employee1);
		System.out.printf("employeeRoles (employee1): \"%s\";\n", employee1.getEmployeeRoles());
		System.out.printf("employee2: \"%s\";\n", employee2);
		System.out.printf("employeeRoles (employee2): \"%s\";\n", employee2.getEmployeeRoles());
		
	}
}