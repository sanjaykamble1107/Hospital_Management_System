package com.hms.entity;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "on_call")
@Data
public class OnCall {
	@EmbeddedId
	private OnCallCompositeId callCompositeId;
}

