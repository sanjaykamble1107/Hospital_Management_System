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
@Data
@Table(name = "patient")

public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patientSeq")
	@SequenceGenerator(name = "patientSeq", initialValue = 100000005, allocationSize = 1)
	@Column(name = "SSN")
	private Integer ssn;
	@Column(name = "Name")
	private String name;
	@Column(name = "Address")
	private String address;
	@Column(name = "Phone")
	private String phone;
	@Column(name = "InsuranceID")
	private Integer insuranceID;
	
	@ManyToOne
	@JoinColumn(name = "PCP")
	private Physician pcp;
}
