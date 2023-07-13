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
@Table(name = "medication")
@Data

public class Medication {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicineSeq")
	@SequenceGenerator(name = "medicineSeq", initialValue = 6, allocationSize = 1)
	@Column(name = "Code")
	private Integer code;
	@Column(name = "Name")
	private String name;
	@Column(name = "Brand")
	private String branch;
	@Column(name = "Description")
	private String description;
}