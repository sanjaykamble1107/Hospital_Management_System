package com.hms.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class PrescribesCompositeId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2170223194558090218L;
	
	
	@Column(name="Date")
	private LocalDateTime startDateTime;
	
	
	@ManyToOne
	@JoinColumn(name="Physician")
	private Physician physician;
	
	@ManyToOne
	@JoinColumn(name="Patient")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name="Medication")
	private Medication medication;
}
