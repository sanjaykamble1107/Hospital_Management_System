package com.hms.entity;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name="affiliated_with")
@Entity
public class Affiliated_With {
	
	@EmbeddedId
	private AffiliatedWithComposite composite;
	
	@Column(name = "PrimaryAffiliation")
	private Boolean primaryaffiliation;
}
