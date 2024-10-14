package com.drishti.completablefuture.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private String newJoiner;
	private String learningPending;
	private String salary;
	private String rating;

}
