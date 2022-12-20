package com.redis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redis.dto.LoginDto;
import com.redis.dto.UserDto;
import com.redis.entities.UserEntity;
import com.redis.repo.UserRepo;
import com.redis.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	UserRepo repo;

	@PostMapping("/admin")
	public UserEntity addUser(@RequestBody UserEntity user) {
		return this.userService.addUser(user);
	}

	@GetMapping("/admin")
	public List<UserEntity> getAllUsers() {
		return this.userService.getAllUsers();
	}

	@GetMapping("/admin/pagination")
	public List<UserEntity> getAllUsersWithPagination(@RequestParam Integer pageNumber,
			@RequestParam Integer pageSize) {

		List<UserEntity> pagination = this.userService.getAllUsersWithPagination(pageNumber, pageSize);
		return pagination;
	}

	@GetMapping("/admin/{id}")
	public UserEntity getUserById(@RequestParam(value = "id") long id) throws Exception {
		return this.userService.getUserById(id);
	}

//	@GetMapping("/admin/name")
//	public List<UserEntity> getUserByName(@RequestParam(value = "name") String name) throws Exception {
//		List<UserEntity> getName = this.userService.getUserByName(name);
//
//		return getName;
//	}

	@PutMapping("/admin/{id}")
	public UserEntity editUserById(@PathVariable(value = "id") long id, @RequestBody UserEntity user) throws Exception {
		return this.userService.editUserById(id, user);
	}

	@DeleteMapping("/admin/{id}")
	public String deleteUserById(@PathVariable(value = "id") long id) {
		this.userService.deleteUserById(id);
		return "Thank You !!! \nId " + id + " Delete Sucessfully";
	}

	@GetMapping("/")
	public List<UserDto> getAllUsersUsingDto() {

		List<UserDto> dto = userService.findByAllUsingDto();
		return dto;

	}

//	@GetMapping("/pagination")
//	public List<UserDto> paginationWithDto(  @RequestParam Integer pageNumber , @RequestParam Integer pageSize  ) {
//		
//		List<UserDto> pagination=this.userService.getAllUsersWithPagination(pageNumber, pageSize);
//		return pagination;
//	}
//	@Autowired
//	private PasswordEncoder bcryptEncoder;
	 

	@GetMapping("/{id}")
	public UserDto getByDtoId(@RequestParam(value = "id") long id) throws Exception {
		UserDto userDto = userService.findByIdUsingDto(id);
		return userDto;
	}

	@PostMapping("/login")
	public String login(@RequestBody LoginDto dto) {

		UserEntity entity = repo.findByName(dto.getName());
		
		 
		
		if(entity.getPassword().equals(dto.getPassword())) {
			 System.out.println("hee");
			 return "hello vinay";
		}
		else {
			System.out.println("bye");
			return "faild";
		}
		
	}

}
