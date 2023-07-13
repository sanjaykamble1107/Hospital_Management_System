package com.hms.service.impl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.hms.dto.UserDto;
import com.hms.entity.User;
import com.hms.repository.UserRepository;
import com.hms.service.inter.IUserService;

import java.util.Optional;


@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDto getUser(Integer userId) {
        UserDto userDto = null;
        Optional<User> optionalUser = userRepo.findById(userId);
        if(optionalUser.isPresent()){
            userDto = optionalUser.get().mapUsertoUserDto();
        }
        return userDto;
    }

	public void signUp(User user) {
		// TODO Auto-generated method stub
		
		
	}


	


}
