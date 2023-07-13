package com.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hms.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer>{
    @Query("SELECT r FROM Room r WHERE r.unavailable = false")
	List<Room> findUnavailabeAll();

}
