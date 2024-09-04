package com.boot.reactive.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.boot.reactive.dao.EmployeeDao;
import com.boot.reactive.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeHandler {
	
	@Autowired
	private EmployeeDao dao;
	
	public Mono<ServerResponse> loadAllEmployees(ServerRequest request){
		Flux<Employee> employees = dao.getAllEmployees();
		return ServerResponse.ok().body(employees, Employee.class);
	}
	
	public Mono<ServerResponse> loadEmployeeById(ServerRequest request){
		Integer id = Integer.valueOf(request.pathVariable("input"));
		Flux<Employee> employees = dao.getAllEmployees();
		//Mono<Employee> employeeMono = employees.filter(e -> e.getEmpId() == id).take(1).single();
		Mono<Employee> employeeMono = employees.filter(e -> e.getEmpId() == id).next();
		return ServerResponse.ok().body(employeeMono, Employee.class);
	}
	
	public Mono<ServerResponse> registerEmployee(ServerRequest request){
		Mono<Employee> employeeMono = request.bodyToMono(Employee.class);
		Mono<String> saveResponse = employeeMono.map(emp -> emp.getEmpId() + ":" + emp.getEmpName());
		return ServerResponse.ok().body(saveResponse, String.class);
	}
}
