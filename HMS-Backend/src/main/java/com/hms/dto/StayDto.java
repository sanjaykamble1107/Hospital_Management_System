package com.hms.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StayDto {
	private Integer stayId;
	@NotNull(message = "Name should not be empty")
	private Integer patient;
	@NotNull(message = "Room should not be empty")
	private Integer room;
	@NotNull(message = "Stay End should not be empty")
	private LocalDate stayEnd;
	@NotNull(message = "Stay Start should not be empty")
	private LocalDate stayStart;
}
