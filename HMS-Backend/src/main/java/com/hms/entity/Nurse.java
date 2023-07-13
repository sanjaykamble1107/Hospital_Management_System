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
@Table(name = "nurse")
@Data
public class Nurse {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nurseSeq")
	@SequenceGenerator(name = "nurseSeq", initialValue = 104, allocationSize = 1)
	@Column(name = "EmployeeID")
	private Integer employeeId;
	@Column(name = "Name")
	private String name;
	@Column(name = "Position")
	private String position;
	@Column(name = "Registered")
	private Boolean registered;
	@Column(name = "SSN")
	private Integer ssn;
}
