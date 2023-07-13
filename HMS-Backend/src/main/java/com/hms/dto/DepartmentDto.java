package com.hms.dto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class DepartmentDto {
	private Integer departmentId;
	@NotNull(message = "Name should not be empty")
	private String name;
	@NotNull(message = "Head should not be empty")
	private Integer head;
}
