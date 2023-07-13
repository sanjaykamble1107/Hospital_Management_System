package com.hms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PhysicianDto {

	private Integer employeeId;
	@NotNull(message = "Name should not be empty")
	private String name;
	@NotNull(message = "Position should not be empty")
	private String position;
	@NotNull(message = "SSN should not be empty")
	private Integer ssn;

}
