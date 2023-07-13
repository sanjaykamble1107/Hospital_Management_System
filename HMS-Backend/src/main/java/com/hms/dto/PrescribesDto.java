package com.hms.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PrescribesDto {
	@NotNull(message = "Physician should not be empty")
	private Integer physician;
	@NotNull(message = "Patient should not be empty")
	private Integer patient;
	@NotNull(message = "Medication should not be empty")
	private Integer medication;
	@NotNull(message = "Date should not be empty")
	private LocalDateTime date;
	private Integer appointment;
	@NotNull(message = "Dose should not be empty")
	private String dose;

}
