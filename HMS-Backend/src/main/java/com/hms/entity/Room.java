package com.hms.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "room")
@Entity
public class Room {

	@Id
	@Column(name = "RoomNumber")
	private Integer roomNumber;
	@Column(name = "RoomType")
	private String roomType;
	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "blockFloor", referencedColumnName = "BlockFloor"),
			@JoinColumn(name = "blockCode", referencedColumnName = "BlockCode") })
	private Block block;
	@Column(name = "Unavailable", columnDefinition = "tinyint(1)")
	private Boolean unavailable;

	@OneToMany(mappedBy = "room")
	private List<Stay> stay;

}
