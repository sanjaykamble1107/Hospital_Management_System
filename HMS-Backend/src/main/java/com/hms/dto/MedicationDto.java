package com.hms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MedicationDto {
	private Integer code;
	@NotNull(message = "Name should not be empty")
	private String name;
	@NotNull(message = "Brand should not be empty")
	private String brand;
	@NotNull(message = "Description should not be empty")
	private String description;
}