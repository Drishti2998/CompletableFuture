package com.drishti.completablefuture.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.drishti.completablefuture.dto.Employee;
import com.drishti.completablefuture.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplyAsyncDemo {

	private final EmployeeRepository employeeRepository;

	public CompletableFuture<List<Employee>> getEmployeeSupply() {
		Executor ex = Executors.newFixedThreadPool(5);
		CompletableFuture<List<Employee>> list = CompletableFuture.supplyAsync(() -> {
			System.out.println("Current thread name 1-->" + Thread.currentThread().getName());
			List<Employee> empList = employeeRepository.fetchEmployee();
			return empList;
		}).exceptionallyAsync(e -> {
			System.out.println("Getting exception if any");
			return null;
		}).thenApplyAsync(res -> {
			System.out.println("Current thread name 2-->" + Thread.currentThread().getName());
			return res.stream().filter(emp -> emp.getFirstName().startsWith("A") || emp.getFirstName().startsWith("a"))
					.collect(Collectors.toList());
		}, ex);
		return list;

	}

}
