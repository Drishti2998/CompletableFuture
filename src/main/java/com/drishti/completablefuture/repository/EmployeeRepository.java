package com.drishti.completablefuture.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.drishti.completablefuture.dto.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeRepository {
	
	public List<Employee> fetchEmployee(){
		ObjectMapper mapper = new ObjectMapper();

		List<Employee> empList = new ArrayList<Employee>();
		try {
			empList = mapper.readValue(new File("employee.json"), new TypeReference<List<Employee>>() {
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Size"+empList.size());
		return empList;
		
	}

}
