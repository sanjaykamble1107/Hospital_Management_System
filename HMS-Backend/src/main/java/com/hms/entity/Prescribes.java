package com.hms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="prescribes")
@Data
public class Prescribes {
	
	@EmbeddedId
	private PrescribesCompositeId prescribesCompositeId;
	
	
	@Column(name="Appointment")
	private Integer appointment;
	
	@Column(name="Dose")
	private String dose;
	
	
}
