package com.hms.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Embeddable
@Data
public class UndergoesCompositeId implements Serializable {

	private static final long serialVersionUID = 8935796606897230001L;
	@ManyToOne
	@JoinColumn(name = "Patient")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "Procedures")
	private Procedures procedure;
	
	@ManyToOne
	@JoinColumn(name = "Stay")
	private Stay stay;
		
	@Column(name = "DateUndergoes", columnDefinition = "DATETIME default '1900-01-01 00:00:00'")
	private LocalDateTime date;
}
