package com.hms.service.inter;

import java.util.List;

import com.hms.dto.RoomDto;

public interface IRoomService {
	public List<RoomDto> getAvailableRooms();
}
