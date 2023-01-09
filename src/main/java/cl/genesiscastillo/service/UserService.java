package cl.genesiscastillo.service;

import java.util.List;
import java.util.Optional;

import cl.genesiscastillo.entity.User;
import cl.genesiscastillo.exception.NotFoundUserByEmailException;
import cl.genesiscastillo.vo.UserVO;

public interface UserService {
	User saveUser(UserVO userVO);
	Optional<User> findByEmail(String email) throws NotFoundUserByEmailException;
	List<User> findAllUser();
}
