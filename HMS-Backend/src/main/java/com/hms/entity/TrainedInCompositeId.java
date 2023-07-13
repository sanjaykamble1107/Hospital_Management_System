package com.hms.entity;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class TrainedInCompositeId implements Serializable {
	private static final long serialVersionUID = 8802775447367441548L;

	@ManyToOne
	@JoinColumn(name = "Physician")
	private Physician physician;

	@ManyToOne
	@JoinColumn(name = "Treatment")
	private Procedures treatment;

}