package com.hms.entity;



import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "block")
@Data
public class Block {

	@EmbeddedId
	private BlockCompositeId blockComposite;

}
