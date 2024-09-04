package com.boot.reactive.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.boot.reactive.handler.EmployeeHandler;
import com.boot.reactive.handler.EmployeeHandlerStream;

@Configuration
public class EmployeeRouterConfig {
	
	@Autowired
	private EmployeeHandler employeeHandler;
	
	@Autowired
	private EmployeeHandlerStream employeeHandlerStream;
	
	@Bean
	public RouterFunction<ServerResponse> routerFunction(){
		return RouterFunctions.route()
				.GET("/router/employees", employeeHandler::loadAllEmployees)
				.GET("/router/employees/stream", employeeHandlerStream::loadAllEmployeesStream)
				.GET("/router/employee/{input}",employeeHandler::loadEmployeeById)
				.POST("/router/register/employee", employeeHandler::registerEmployee)
				.build();
	}
	
}
