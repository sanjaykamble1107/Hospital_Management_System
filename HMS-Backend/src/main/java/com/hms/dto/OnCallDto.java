package com.hms.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OnCallDto {
	@NotNull(message = "Nurse should not be empty")
	private Integer nurse;
	@NotNull(message = "Block Floor should not be empty")
	private Integer blockFloor;
	@NotNull(message = "Block Code should not be empty")
	private Integer blockCode;
	@NotNull(message = "On Call Start should not be empty")
	private LocalDateTime onCallStart;
	@NotNull(message = "On Call End should not be empty")
	private LocalDateTime onCallEnd;
}
