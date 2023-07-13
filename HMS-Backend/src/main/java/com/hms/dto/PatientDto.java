package com.hms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientDto {

	private Integer ssn;
	@NotNull(message = "Name should not be empty")
	private String name;
	@NotNull(message = "Address should not be empty")
	private String address;
	@NotNull(message = "Phone should not be empty")
	@Size(max = 10, min = 10, message = "Phone Number Should be 10 Digits")
	private String phone;
	@NotNull(message = "Insuarance ID should not be empty")
	private Integer insuranceID;
	@NotNull(message = "PCP should not be empty")
	private Integer pcp;

}
