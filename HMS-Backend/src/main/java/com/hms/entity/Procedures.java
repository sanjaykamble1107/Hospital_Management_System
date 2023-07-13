package com.hms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "procedures")
@Entity
public class Procedures {
	
	@Id
	@Column(name = "Code")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "procedureSeq")
	@SequenceGenerator(name = "seq", initialValue = 8, allocationSize = 1)
	private Integer code;
	@Column(name = "Name")
	private String name;
	@Column(name = "Cost")
	private double cost;
	
}
