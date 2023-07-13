package com.hms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Affiliated_WithDto {

	@NotNull(message = "Physician should not be empty")
	private Integer physician;
	@NotNull(message = "Department should not be empty")
	private Integer department;
	@NotNull(message = "Primary Affiliation should not be empty")
	private Boolean primaryAffiliation;
}
