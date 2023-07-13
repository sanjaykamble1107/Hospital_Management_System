package com.hms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoomDto {
	@NotNull(message = "Room Number should not be empty")
	private Integer roomNumber;
	@NotNull(message = "Room Type should not be empty")
	private String roomType;
	@NotNull(message = "Block Code should not be empty")
	private Integer blockCode;
	@NotNull(message = "Block Floor should not be empty")
	private Integer blockFloor;
	@NotNull(message = "Unavailable should not be empty")
	private Boolean unavailable;
}