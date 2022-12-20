package com.redis.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.redis.config.utils.AppSetting;
import com.redis.dto.UserDto;
import com.redis.entities.UserEntity;
import com.redis.repo.UserRepo;
import com.redis.service.UserService;

@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserRepo userRepo;

	@Autowired
	private AppSetting appSetting;

	@Override
	public UserEntity addUser(UserEntity user) 
	{
		UserEntity newUser = new UserEntity();
		newUser.setId(user.getId());
		newUser.setName(user.getName());
		newUser.setAddress(user.getAddress());
		newUser.setMobile(user.getMobile());
		newUser.setPassword(appSetting.getAllAppSetting().getSettings().get("chalNigh"));
		return this.userRepo.save(newUser);
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return this.userRepo.findAll();
	}

	@Override
	public List<UserEntity> getAllUsersWithPagination(Integer pageNumber, Integer pageSize) {

		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
		Page<UserEntity> page = this.userRepo.findAll(pageable);

		List<UserEntity> list = page.getContent();

		return list;
	}

	@Override
	public UserEntity getUserById(long id) throws Exception {
		return this.userRepo.findById(id).orElseThrow(() -> new Exception("User Not Found on Given Id " + id));
	}

//	@Override
//	public List<UserEntity> getUserByName(String name) {
//		List<UserEntity> getName = this.userRepo.findByName(name);
//
//		return getName;
//	}

	@Override
	public UserEntity editUserById(long id, UserEntity user) throws Exception {

		UserEntity updateUser = this.userRepo.findById(id)
				.orElseThrow(() -> new Exception("User Not Found on Given Id " + id));

		updateUser.setId(user.getId());
		updateUser.setName(user.getName());
		updateUser.setAddress(user.getAddress());
		updateUser.setMobile(user.getMobile());
		updateUser.setPassword(user.getPassword());
		System.out.println("@@@@@@@"+user.getPassword());
		return this.userRepo.save(updateUser);
	}

	@Override
	public void deleteUserById(long id) {
		this.userRepo.deleteById(id);
	}

	// dto - user

	@Override
	public List<UserDto> findByAllUsingDto() {

		List<UserEntity> user = this.userRepo.findAll(); // take data
		List<UserDto> dto = new ArrayList<>(); // create list

		for (int i = 0; i < user.size(); i++) {

			UserDto userDto = new UserDto();
			userDto.setName(user.get(i).getName());
			userDto.setAddress(user.get(i).getAddress());
			userDto.setMobile(user.get(i).getMobile());

			dto.add(userDto);
		}

		return dto;
	}

	@Override
	public UserDto findByIdUsingDto(long id) throws Exception {

		UserEntity user = userRepo.findById(id).orElseThrow(() -> new Exception("User Not Found on Given Id " + id));

		UserDto userDto = new UserDto();

		userDto.setName(user.getName());
		userDto.setAddress(user.getAddress());
		userDto.setMobile(user.getMobile());

		return userDto;
	}
}
