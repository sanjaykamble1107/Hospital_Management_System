package com.hms.entity;

import java.time.LocalDateTime;

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
@Table(name = "appointment")
@Data
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointmentSeq")
	@SequenceGenerator(name = "appointmentSeq", initialValue = 93216549, allocationSize = 1)
	@Column(name = "AppointmentID")
	private Integer appointmentId;

	@ManyToOne
	@JoinColumn(name = "Patient")
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "PrepNurse")
	private Nurse prepNurse;

	@ManyToOne
	@JoinColumn(name = "Physician")
	private Physician physician;

	@Column(name = "Starto", columnDefinition = "DATETIME default '1900-01-01 00:00:00'")
	private LocalDateTime startDateTime;

	@Column(name = "Endo", columnDefinition = "DATETIME default '1900-01-01 00:00:00'")
	private LocalDateTime endDateTime;

	@ManyToOne
	@JoinColumn(name = "Room")
	private Room room;

}
