package com.hms.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "trained_in")
@Data
public class TrainedIn {
	@EmbeddedId
	private TrainedInCompositeId compositeId;

	@Column(name = "CertificationDate", columnDefinition = "DATETIME default '1900-01-01 00:00:00'")
	private LocalDateTime certificationDate;

	@Column(name = "CertificationExpires", columnDefinition = "DATETIME default '1900-01-01 00:00:00'")
	private LocalDateTime certificationExpires;

}
