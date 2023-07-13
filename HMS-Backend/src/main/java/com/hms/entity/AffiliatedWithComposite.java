package com.hms.entity;

import java.io.Serializable;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class AffiliatedWithComposite implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2674424968194996831L;
	
	
	@ManyToOne
	@JoinColumn(name="Physician")
	private Physician physician;
	
	@ManyToOne
	@JoinColumn(name="Department")
	private Department department;

}
