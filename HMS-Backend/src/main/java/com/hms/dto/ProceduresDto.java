package com.hms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProceduresDto {
	
	private Integer code;
	@NotNull(message = "Name should not be empty")
	private String name;
	@NotNull(message = "Code should not be empty")
	private double cost;
}
