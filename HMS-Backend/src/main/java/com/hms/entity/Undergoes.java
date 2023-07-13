package com.hms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "undergoes")
@Entity
public class Undergoes {

	@EmbeddedId
	private UndergoesCompositeId undergoesCompositeId;
	@ManyToOne
	@JoinColumn(name = "Physician")
	private Physician physician;
	@Column(name = "AssistingNurse")
	private Integer assistingnurse;

}
