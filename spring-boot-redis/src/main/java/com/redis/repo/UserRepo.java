package com.redis.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redis.entities.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {	
	
	public UserEntity findByName(String name);

//	public List<UserDto> findByNameUsingDto(String name);
	
}
