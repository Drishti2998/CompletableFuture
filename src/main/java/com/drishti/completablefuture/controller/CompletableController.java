package com.drishti.completablefuture.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.drishti.completablefuture.dto.Employee;
import com.drishti.completablefuture.service.SupplyAsyncDemo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CompletableController {
	private final SupplyAsyncDemo supplyAsyncDemo;
	
	@GetMapping("/fetchEmployee")
	@ResponseStatus(HttpStatus.OK)
	public CompletableFuture<List<Employee>> fetchEmployee() {
		return supplyAsyncDemo.getEmployeeSupply();
	}
	
	

}
