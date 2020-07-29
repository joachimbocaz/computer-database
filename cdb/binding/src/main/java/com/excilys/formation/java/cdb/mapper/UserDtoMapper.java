package com.excilys.formation.java.cdb.mapper;

import com.excilys.formation.java.cdb.dto.UserDto;
import com.excilys.formation.java.cdb.model.User;

public class UserDtoMapper {
		
	public static UserDto userToDto(User user) {
		return new UserDto(user.getId().toString(), user.getUsername(), user.getPassword(), user.getRole());
	}
	
	public static User dtoToUser(UserDto userDto) {
		User user = new User();
		user.setId(Long.getLong(userDto.getId()));
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setRole(userDto.getRole());
		return user;		
	}
}
