package com.redis.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.redis.dto.UserDto;
import com.redis.entities.UserEntity;

public interface UserService {
	
	// entity - admin
	
	public UserEntity addUser(UserEntity user);
	
	public List<UserEntity> getAllUsers();
	
	public List<UserEntity> getAllUsersWithPagination(Integer pageNumber, Integer pageSize);
	
	public UserEntity getUserById(long id) throws Exception;
	
//	public List<UserEntity> getUserByName(String name);
	
	public UserEntity editUserById(long id, UserEntity user) throws Exception;
	
	public void deleteUserById(long id);

	
	// dto - user 
	
	public List<UserDto> findByAllUsingDto();
	
//	public Page<UserDto> paginationWithDto(Integer pageNumber, Integer pageSize);

	public UserDto findByIdUsingDto(long id) throws Exception;
		
//	public List<UserDto> findByNameUsingDto(String name);
	
	
	

}
