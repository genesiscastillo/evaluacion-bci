package cl.genesiscastillo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import cl.genesiscastillo.entity.Phone;
import cl.genesiscastillo.entity.User;
import cl.genesiscastillo.exception.NotFoundUserByEmailException;
import cl.genesiscastillo.repository.PhoneRepository;
import cl.genesiscastillo.repository.UserRepository;

public class UserServiceImplTest {
	
	static final String USER_UUID 		= "67c26dfb-63ee-4ebc-b869-57bb3194af6e";
	static final String USER_NAME 		= "cesar castillo";
	static final String USER_EMAIL 		= "cepija6292@kaftee.com"; 	
	static final String USER_PASSWORD 	= "6oR9pSPj!Xg1";
	static final String USER_TOKEN 		= "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRta";	
	static final Collection<Phone> USER_PHONES = Collections.emptyList();

	@Test
	void testUserService() throws Exception {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);
		
		Mockito.when(userRepository.findByEmail("email")).thenReturn(null);

		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.setPhoneRepository(phoneRepository);
		userServiceImpl.setUserRepsoitory(userRepository);
		
		Assertions.assertThrows(NotFoundUserByEmailException.class  , ()-> userServiceImpl.findByEmail("email"));
	}
	
	@Test
	void testUserService2() throws Exception {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);
		User user = new User(USER_NAME, USER_EMAIL, USER_PASSWORD, USER_TOKEN, USER_PHONES);
		Mockito.when(userRepository.findByEmail("email")).thenReturn(user);

		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.setPhoneRepository(phoneRepository);
		userServiceImpl.setUserRepsoitory(userRepository);
		
		Optional<User> optional = userServiceImpl.findByEmail("email");
		
		Assertions.assertTrue(optional.isPresent());
	}
	
	@Test
	void testUserService3() throws Exception {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);

		Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>());

		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.setPhoneRepository(phoneRepository);
		userServiceImpl.setUserRepsoitory(userRepository);
		
		List<User> users = userServiceImpl.findAllUser();
		
		Assertions.assertTrue(users.isEmpty());
	}

}
