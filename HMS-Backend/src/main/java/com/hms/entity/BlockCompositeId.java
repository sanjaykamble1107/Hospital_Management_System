package com.hms.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class BlockCompositeId implements Serializable {
	private static final long serialVersionUID = -3687785550902983298L;

	@Column(name = "BlockFloor")
	private Integer blockFloor;

	@Column(name = "BlockCode")
	private Integer blockCode;
}
