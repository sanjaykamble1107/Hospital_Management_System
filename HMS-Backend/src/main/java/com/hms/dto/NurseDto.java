package com.hms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NurseDto {
	private Integer employeeId;
	@NotNull(message = "Name should not be empty")
	private String name;
	@NotNull(message = "Position should not be empty")
	private String position;
	@NotNull(message = "Registered should not be empty")
	private Boolean registered;
	@NotNull(message = "SSN should not be empty")
	private Integer ssn;
}
