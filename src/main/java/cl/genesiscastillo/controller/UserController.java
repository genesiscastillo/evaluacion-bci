package cl.genesiscastillo.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.genesiscastillo.entity.User;
import cl.genesiscastillo.exception.ErrorBussinesException;
import cl.genesiscastillo.service.UserServiceImpl;
import cl.genesiscastillo.vo.UserRequest;
import cl.genesiscastillo.vo.UserResponse;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserServiceImpl userService;
	
	@PostMapping("/users")
	UserResponse postUser(@RequestBody UserRequest userVO) throws ErrorBussinesException {
		return userService.createUser(userVO);
	}

	@GetMapping("/users")
	ResponseEntity<List<User>> getUser() throws ErrorBussinesException {
		List<User> users = new ArrayList<User>();
		userService.findAllUser().forEach(users::add);
		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	UserResponse getUserById(@PathVariable("id") String id) throws ErrorBussinesException {
		return userService.findById(id);
	}
	
	@PutMapping("/users/{id}")
	UserResponse putUser(@PathVariable("id") String id ,@RequestBody UserRequest userRequest) throws ErrorBussinesException {
		return userService.updateUser(id , userRequest);
	}
	
	@DeleteMapping("/users/{id}")
	void deleteUser(@PathVariable("id") String id ) throws ErrorBussinesException {
		userService.deleteUserById(id);
	}
}
