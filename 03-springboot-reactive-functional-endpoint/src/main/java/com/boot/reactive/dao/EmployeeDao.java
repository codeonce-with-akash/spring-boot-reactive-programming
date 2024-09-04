package com.boot.reactive.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.boot.reactive.model.Employee;

import reactor.core.publisher.Flux;

@Component
public class EmployeeDao {
	
	public Flux<Employee> getAllEmployees(){
		return Flux.range(1, 50)
				.doOnNext(i -> System.out.println("Processing count :: "+i))
				.map((i) -> new Employee(i, "Employee:"+i));
	}
	
	public Flux<Employee> getALlEmployeesStream(){
		return Flux.range(1, 50)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(i -> System.out.println("Processing Count :: "+i))
				.map(i -> new Employee(i, "Employee:"+i));
	}
}
