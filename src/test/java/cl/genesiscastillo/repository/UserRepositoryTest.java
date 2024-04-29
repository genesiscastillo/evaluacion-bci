package cl.genesiscastillo.repository;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import cl.genesiscastillo.entity.Phone;

@DataJpaTest
public class UserRepositoryTest {
	
	static final String USER_UUID 		= "67c26dfb-63ee-4ebc-b869-57bb3194af6e";
	static final String USER_NAME 		= "cesar castillo";
	static final String USER_EMAIL 		= "cepija6292@kaftee.com"; 	
	static final String USER_PASSWORD 	= "6oR9pSPj!Xg1";
	static final String USER_TOKEN 		= "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRta";	
	static final Collection<Phone> USER_PHONES = Collections.emptyList();
	
	@Autowired
	TestEntityManager  entityManager;

	@Autowired
	UserRepository userRepository;

	@Test
	void findUserByEmail() {
//		User user = new User(USER_NAME,USER_EMAIL,USER_PASSWORD, USER_TOKEN, USER_PHONES );
//		user  = userRepository.save(user);
//		
//		Assertions.assertNotNull(user.getId());
//		
//		User userFound =	userRepository.findByEmail(USER_EMAIL);
//		Assertions.assertNotNull(userFound);
	}
	
	@Test
	void createNewUser() {
//		User user = new User(USER_NAME,USER_EMAIL,USER_PASSWORD, USER_TOKEN, USER_PHONES );
//		user  = userRepository.save(user);
//		
//		Assertions.assertNotNull(user.getId());
//		Assertions.assertNotNull(user.getCreated());		
//		Assertions.assertNull(user.getModified());
//		Assertions.assertNotNull(user.generateToken());
//		Assertions.assertTrue(user.getIsactive());
	}

	@Test
	void changeActiveToUser() {
//		User user = new User(USER_NAME,USER_EMAIL,USER_PASSWORD, USER_TOKEN, USER_PHONES );
//		user  = userRepository.save(user);
//		
//		user.setIsactive(Boolean.FALSE);
//		user  = userRepository.saveAndFlush(user);
//
//		Assertions.assertNotNull(user.getModified());
//		Assertions.assertFalse(user.getIsactive());
	}

	@Test
	void updateLastLoginToUser() {
//		User user = new User(USER_NAME,USER_EMAIL,USER_PASSWORD, USER_TOKEN,USER_PHONES );
//		user  = userRepository.save(user);
//		
//		user.setLast_login(LocalDateTime.now());
//		user  = userRepository.saveAndFlush(user);
//
//		Assertions.assertNotNull(user.getLast_login());
	}
	
}
