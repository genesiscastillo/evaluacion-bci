package cl.genesiscastillo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.genesiscastillo.entity.User;
import cl.genesiscastillo.exception.NotFoundUserByEmailException;
import cl.genesiscastillo.service.UserService;
import cl.genesiscastillo.vo.UserVO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping
	public ResponseEntity<User> getUser(@RequestParam("email") String email) throws NotFoundUserByEmailException {
		Optional<User> optional = userService.findByEmail(email);
		
		if(optional.isEmpty()) {
			throw new NotFoundUserByEmailException();
		}
		return ResponseEntity.ok().body(optional.get());
	}

	@PostMapping
	public ResponseEntity<User> postUser(@Valid @RequestBody UserVO userVO) {
		User user = userService.saveUser(userVO);
		return new ResponseEntity<>(user , HttpStatus.CREATED);
	}

}
