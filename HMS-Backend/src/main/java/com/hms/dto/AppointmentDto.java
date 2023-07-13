package com.hms.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppointmentDto {

	private Integer appointmentId;
	@NotNull(message = "Patient should not be empty")
	private Integer patient;
	private Integer prepNurse;
	@NotNull(message = "Physician should not be empty")
	private Integer physician;
	@NotNull(message = "Start Date Time should not be empty")
	private LocalDateTime startDateTime;
	@NotNull(message = "End Date Time should not be empty")
	private LocalDateTime endDateTime;
	@NotNull(message = "Room should not be empty")
	private Integer examinationRoom;
}
