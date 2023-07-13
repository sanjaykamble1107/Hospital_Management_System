package com.hms.entity;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "stay")
public class Stay {

	@Id
	@Column(name = "Patient")
	private Integer patient;

	@ManyToOne
	@JoinColumn(name="Room")
	private Room room;
	
	@Column(name = "StayEnd")
	private LocalDateTime stayEnd;
	@Column(name = "StayID")
	private Integer stayId;
	@Column(name = "StayStart")
	private LocalDateTime stayStart;
}
