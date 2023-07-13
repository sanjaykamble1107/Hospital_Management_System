package com.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.dto.RoomDto;
import com.hms.service.inter.IRoomService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/room")
public class RoomController {
	
	@Autowired
	private IRoomService roomService;
	
	@GetMapping("/available")
		public ResponseEntity<List<RoomDto>> getAvailableRooms(){
			List<RoomDto> availableRooms = roomService.getAvailableRooms();			
			return new ResponseEntity<List<RoomDto>> (availableRooms,HttpStatus.OK);	
		}
		
}
