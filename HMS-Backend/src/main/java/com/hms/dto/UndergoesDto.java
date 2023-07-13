package com.hms.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UndergoesDto {

	@NotNull(message = "Patient should not be empty")
	private Integer patient;
	@NotNull(message = "Procedure should not be empty")
	private Integer procedure;
	@NotNull(message = "Stay should not be empty")
	private Integer stay;
	@NotNull(message = "Date should not be empty")
	private LocalDate date;
	@NotNull(message = "Physician should not be empty")
	private Integer physician;
	private Integer assistingnurse;
}
