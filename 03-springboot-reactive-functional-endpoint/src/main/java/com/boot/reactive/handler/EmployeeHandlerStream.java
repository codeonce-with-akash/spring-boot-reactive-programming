package com.boot.reactive.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.boot.reactive.dao.EmployeeDao;
import com.boot.reactive.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeHandlerStream {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	public Mono<ServerResponse> loadAllEmployeesStream(ServerRequest request){
		Flux<Employee> employeesStream = employeeDao.getALlEmployeesStream();
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(employeesStream, Employee.class);
	}
}
