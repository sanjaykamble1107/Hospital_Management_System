package com.hms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.dto.RoomDto;
import com.hms.entity.Room;
import com.hms.repository.RoomRepository;
import com.hms.service.inter.IRoomService;

@Service
public class RoomService implements IRoomService{

	@Autowired
	private RoomRepository roomRepository;
	
	@Override
	public List<RoomDto> getAvailableRooms() {
		List<Room> findUnavailabeAll = roomRepository.findUnavailabeAll();
		List<RoomDto> roomDto = new ArrayList<>();
		for(Room room: findUnavailabeAll) {
			RoomDto rto = new RoomDto();
			rto.setRoomNumber(room.getRoomNumber());
			rto.setRoomType(room.getRoomType());
			rto.setBlockCode(room.getBlock().getBlockComposite().getBlockCode());
			rto.setBlockFloor(room.getBlock().getBlockComposite().getBlockFloor());
			rto.setUnavailable(room.getUnavailable());
			roomDto.add(rto);
		}
		return roomDto;
	}

}
