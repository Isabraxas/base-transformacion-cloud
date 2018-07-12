package com.javainuse.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.model.Employee;

@RestController
public class TestController {

	@Autowired
	private Environment env;

	@Value("${server.port}")
	private String port;

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getDataFallBack")
	public Employee firstPage() {

		System.out.println("Inside firstPage");

		Employee emp = new Employee();
		emp.setName("emp1");
		emp.setDesignation("manager");
		emp.setEmpId("1");
		emp.setSalary(3000);
		emp.setPort(env.getProperty("server.port"));

		System.out.println(emp.getPort());

		//Siempre lanza la excepcion
		if(emp.getName().equalsIgnoreCase("emp1"))
			throw new RuntimeException();

		return emp;
	}

	public Employee getDataFallBack(){

		System.out.println("Inside fallback");

		Employee emp= new Employee();
		emp.setName("fallback-emp1");
		emp.setDesignation("fallback-manager");
		emp.setEmpId("fallback-1");
		emp.setSalary(3000);
		emp.setPort(port);

		return emp;
	}


}