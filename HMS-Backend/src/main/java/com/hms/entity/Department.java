package com.hms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "department")
@Data
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departmentSeq")
	@SequenceGenerator(name = "departmentSeq", initialValue = 6, allocationSize = 1)
	@Column(name = "DepartmentID")
	private Integer departmentId;

	@Column(name = "Name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "Head")
	private Physician head;

}
