package com.hms.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class OnCallCompositeId implements Serializable {
	private static final long serialVersionUID = 7468934864003354939L;

	@Column(name = "OnCallStart", columnDefinition = "DATETIME default '1900-01-01 00:00:00'")
	private LocalDateTime onCallStart;

	@Column(name = "OnCallEnd", columnDefinition = "DATETIME default '1900-01-01 00:00:00'")
	private LocalDateTime onCallEnd;

	@ManyToOne
	@JoinColumn(name = "Nurse")
	private Nurse nurse;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "BlockFloor"), @JoinColumn(name = "BlockCode") })
	private Block block;

}
