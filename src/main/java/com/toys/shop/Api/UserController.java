package com.toys.shop.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.toys.shop.Entities.User;
import com.toys.shop.Repository.UserRepository;

@RestController
public class UserController {
	@Autowired
	UserRepository userRepository;

	@GetMapping(value = { "/users" })
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = userRepository.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@PostMapping(value = { "/user" })
	public ResponseEntity<User> createUser(@RequestBody User newUser) {
		User user = userRepository.save(newUser);
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@DeleteMapping("/user/{Id}")
	public ResponseEntity<String> deleteUser(@PathVariable(name = "Id") Integer id) throws Exception {
		User deleteUser = userRepository.findUserById(id);
		if (deleteUser == null) {
			throw new Exception("Không tồn tại User !");
		}
		return new ResponseEntity<String>("Xóa user thành công", HttpStatus.OK);
	}

	@PutMapping("/user/{Id}")
	public ResponseEntity<User> updateUser(@RequestBody User newUser,
			@PathVariable(name = "Id") Integer id) {
		User user = userRepository.findUserById(id);

		return null;
	}

}
