package com.hms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BlockDto {
	@NotNull(message = "Block Floor should not be empty")
	private Integer blookFloor;
	@NotNull(message = "Block Code should not be empty")
	private Integer blookCode;
}
