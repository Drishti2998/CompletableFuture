package com.drishti.completablefuture.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.drishti.completablefuture.dto.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RunAsyncDemo {

	public Void getEmployees(File jsonFile) throws InterruptedException, ExecutionException {
		ObjectMapper mapper = new ObjectMapper();
		CompletableFuture<Void> runAsyncResult = CompletableFuture.runAsync(() -> {
			try {
				List<Employee> employeeList = mapper.readValue(jsonFile, new TypeReference<List<Employee>>() {
				});
				// repository to save it--> let suppose u want to save instead of get
				System.out.println("Current Thread name--> " + Thread.currentThread().getName());
				long count = employeeList.stream().count();
				System.out.println(count);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		return runAsyncResult.get();
	}
	
	public Void getEmployeesExecutor(File jsonFile) throws InterruptedException, ExecutionException {
		Executor ex = Executors.newFixedThreadPool(5);
		ObjectMapper mapper = new ObjectMapper();
		CompletableFuture<Void> runAsyncResult = CompletableFuture.runAsync(() -> {
			try {
				List<Employee> employeeList = mapper.readValue(jsonFile, new TypeReference<List<Employee>>() {
				});
				
				System.out.println("Current Thread name in exceutor method--> " + Thread.currentThread().getName());
				long count = employeeList.stream().count();
				System.out.println(count);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		},ex);

		return runAsyncResult.get();
	}

	public static void main(String[] args) {
		RunAsyncDemo runAsyncDemo1 = new RunAsyncDemo();
		try {
			System.out.println("Current Thread name--> " + Thread.currentThread().getName());
			runAsyncDemo1.getEmployees(new File("employee.json"));
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RunAsyncDemo runAsyncDemo2 = new RunAsyncDemo();
		try {
			System.out.println("Current Thread name exceutor method--> " + Thread.currentThread().getName());
			runAsyncDemo2.getEmployeesExecutor(new File("employee.json"));
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
