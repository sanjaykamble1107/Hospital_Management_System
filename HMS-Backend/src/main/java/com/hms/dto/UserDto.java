package com.hms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class UserDto {

	private Integer id;
	@NotNull(message = "User Name should not be empty")
	private String username;
	private String password;
	private String roles;
}
