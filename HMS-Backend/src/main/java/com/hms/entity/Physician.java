package com.hms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "physician")
public class Physician {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "physicianSeq")
	@SequenceGenerator(name = "physicianSeq", initialValue = 10, allocationSize = 1)
	@Column(name = "EmployeeID")
	private Integer employeeId;

	@Column(name = "Name")
	private String name;

	@Column(name = "Position")
	private String position;

	@Column(name = "SSN")
	private Integer ssn;
}
