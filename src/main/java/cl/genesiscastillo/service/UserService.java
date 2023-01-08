package cl.genesiscastillo.service;

import java.util.Optional;

import cl.genesiscastillo.entity.User;
import cl.genesiscastillo.vo.UserVO;

public interface UserService {
	User saveUser(UserVO userVO);
	Optional<User> findByEmail(String email);
}
