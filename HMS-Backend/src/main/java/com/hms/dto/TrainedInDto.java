package com.hms.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TrainedInDto {
	@NotNull(message = "Physician should not be empty")
	private Integer physician;
	@NotNull(message = "Treatment should not be empty")
	private Integer treatment;
	@NotNull(message = "Certification Date should not be empty")
	private LocalDateTime certificationDate;
	@NotNull(message = "Certification Expires should not be empty")
	private LocalDateTime certificationExpires;

}
