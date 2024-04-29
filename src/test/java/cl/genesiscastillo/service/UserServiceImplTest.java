package cl.genesiscastillo.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import cl.genesiscastillo.builders.UserBuilder;
import cl.genesiscastillo.entity.Phone;
import cl.genesiscastillo.entity.User;
import cl.genesiscastillo.exception.ErrorBussinesException;
import cl.genesiscastillo.repository.PhoneRepository;
import cl.genesiscastillo.repository.UserRepository;
import cl.genesiscastillo.vo.PhoneRequest;
import cl.genesiscastillo.vo.UserRequest;
import cl.genesiscastillo.vo.UserResponse;

public class UserServiceImplTest {
	
	static final String PASS_EX_REG = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{8,20}$";
	
	static final String USER_UUID 		= "67c26dfb-63ee-4ebc-b869-57bb3194af6e";
	static final String USER_NAME 		= "cesar castillo";
	static final String USER_EMAIL 		= "cepija6292@ccafeetools.com"; 	
	static final String USER_PASSWORD 	= "6oR9pSPj!Xg1";
	static final String USER_TOKEN 		= "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRta";	
	
	static final String PHONE_NUMBER = "99999999";
	static final String PHONE_CITY_CODE = "01";
	static final String PHONE_COUNTRY_CODE = "56";
	
	static final Collection<Phone> USER_PHONES = Collections.emptyList();
	
	UserRequest userRequest = new UserRequest();
	Collection<PhoneRequest> phoneVOs = new ArrayList<PhoneRequest>();
	User user = new User();
	UserResponse userResponse = new UserResponse(USER_UUID, PHONE_NUMBER, PHONE_COUNTRY_CODE, PHONE_CITY_CODE, USER_TOKEN, null);
	
	Optional<User> optionalUser = Optional.empty();
	
	
	@BeforeEach
	public void init() {
		PhoneRequest phoneVO = new PhoneRequest(PHONE_NUMBER, PHONE_CITY_CODE, PHONE_COUNTRY_CODE);
		Assertions.assertNotNull(phoneVO);
		Assertions.assertNotNull(phoneVO.getCitycode());
		Assertions.assertNotNull(phoneVO.getCountrycode());
		Assertions.assertNotNull(phoneVO.getNumber());
		phoneVOs.add(phoneVO);		

		userRequest.setEmail(USER_EMAIL);
		userRequest.setName(USER_NAME);
		userRequest.setPassword(USER_PASSWORD);
		userRequest.setPhones( phoneVOs );
		Assertions.assertNotNull(userRequest.getPassword());
		
		user = new UserBuilder().setEmail(userRequest.getEmail()).setName(userRequest.getName())
				.setPassword(userRequest.getPassword()).setToken("123456789").setPhones(new ArrayList<Phone>())
				.build();
		user.setId("8aba4128-287f-41b7-823d-a76d9253f765");
		user.setCreated(LocalDateTime.now());
		user.setModified(LocalDateTime.now());
		user.setLastLogin(LocalDateTime.now());
		
		optionalUser = Optional.of(user) ;
	}
	
	@Test
	void testUserService() throws Exception {
//		user.
//		Assertions.assertNotNull(user.getName());
//		Assertions.assertNotNull(user.getPassword());
//		Assertions.assertNotNull(user.getPhones());
		
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);
		
		Mockito.when(userRepository.findById("8aba4128-287f-41b7-823d-a76d9253f765")).thenReturn(optionalUser);

		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.setPhoneRepository(phoneRepository);
		userServiceImpl.setUserRepsoitory(userRepository);
		
		Assertions.assertNotNull(userServiceImpl.findById("8aba4128-287f-41b7-823d-a76d9253f765"));
	}

	@Test
	void testUserService1() throws Exception {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);
		
		//Mockito.when(userRepository.findById("8aba4128-287f-41b7-823d-a76d9253f765")).thenReturn(optionalUser);

		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.setPhoneRepository(phoneRepository);
		userServiceImpl.setUserRepsoitory(userRepository);
		
		Assertions.assertThrows(ErrorBussinesException.class , () -> userServiceImpl.findById("8aba4128-287f-41b7-823d-a76d9253f765"));
	}
	
	
	@Test
	void testUserService2() throws Exception {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);
		
		Collection<PhoneRequest> phoneVOs = new ArrayList<PhoneRequest>();		
		PhoneRequest phoneVO = new PhoneRequest(PHONE_NUMBER, PHONE_CITY_CODE, PHONE_COUNTRY_CODE);
		phoneVOs.add(phoneVO);		
		
		UserRequest userRequest = new UserRequest();
		userRequest.setEmail(USER_EMAIL);
		userRequest.setName(USER_NAME);
		userRequest.setPassword(USER_PASSWORD);
		userRequest.setPhones( phoneVOs );
		
		Mockito.when(userRepository.findById("8aba4128-287f-41b7-823d-a76d9253f765")).thenReturn(optionalUser );
		
		Mockito.when(userRepository.existsById(USER_EMAIL)).thenReturn(Boolean.TRUE);
		
		Mockito.when(userRepository.save(user)).thenReturn(user);
		
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.setPhoneRepository(phoneRepository);
		userServiceImpl.setUserRepsoitory(userRepository);
		userServiceImpl.setPasswordExpReg(PASS_EX_REG);
		
		UserResponse userResponse =	userServiceImpl.updateUser("8aba4128-287f-41b7-823d-a76d9253f765", userRequest);
		
		Assertions.assertNotNull(userResponse);
	}

	@Test
	void testUserService22() throws Exception {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);
		
		Collection<PhoneRequest> phoneVOs = new ArrayList<PhoneRequest>();		
		PhoneRequest phoneVO = new PhoneRequest(PHONE_NUMBER, PHONE_CITY_CODE, PHONE_COUNTRY_CODE);
		phoneVOs.add(phoneVO);		
		
		UserRequest userRequest = new UserRequest();
		userRequest.setEmail(USER_EMAIL);
		userRequest.setName(USER_NAME);
		userRequest.setPassword(USER_PASSWORD);
		userRequest.setPhones( phoneVOs );
		
		Mockito.when(userRepository.findById("8aba4128-287f-41b7-823d-a76d9253f765-2")).thenReturn(optionalUser );
		
		Mockito.when(userRepository.existsById(USER_EMAIL)).thenReturn(Boolean.TRUE);
		
		Mockito.when(userRepository.save(user)).thenReturn(user);
		
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.setPhoneRepository(phoneRepository);
		userServiceImpl.setUserRepsoitory(userRepository);
		userServiceImpl.setPasswordExpReg(PASS_EX_REG);
		
		Assertions.assertThrows(ErrorBussinesException.class, () -> userServiceImpl.updateUser("8aba4128-287f-41b7-823d-a76d9253f765", userRequest));
		
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
	
	
	@Test
	void testUserService4() throws Exception {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);
		
		Mockito.when(userRepository.findById("8aba4128-287f-41b7-823d-a76d9253f765")).thenReturn(optionalUser );
		
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.setPhoneRepository(phoneRepository);
		userServiceImpl.setUserRepsoitory(userRepository);
		
		userServiceImpl.deleteUserById("8aba4128-287f-41b7-823d-a76d9253f765");
	}

	@Test
	void testUserService5() throws Exception {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);
		
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.setPhoneRepository(phoneRepository);
		userServiceImpl.setUserRepsoitory(userRepository);
		
		Assertions.assertThrows(ErrorBussinesException.class , () -> userServiceImpl.deleteUserById("8aba4128-287f-41b7-823d-a76d9253f765"));
	}

	@Test
	void testUserService6() throws Exception {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);
		
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.setPhoneRepository(phoneRepository);
		userServiceImpl.setUserRepsoitory(userRepository);
		
		Mockito.when(userRepository.existsById(USER_EMAIL)).thenReturn(Boolean.TRUE);
		
		assertThrows(ErrorBussinesException.class, () -> userServiceImpl.validateExistEmail(USER_EMAIL));
	}

	@Test
	void testUserService7() throws Exception {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);
		
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.setPhoneRepository(phoneRepository);
		userServiceImpl.setUserRepsoitory(userRepository);
	
		assertThrows(ErrorBussinesException.class, () -> userServiceImpl.validateFormatEmail("aaaaaaaaa"));
	}


	@Test
	void testUserService8() throws Exception {
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		PhoneRepository phoneRepository = Mockito.mock(PhoneRepository.class);
		
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.setPhoneRepository(phoneRepository);
		userServiceImpl.setUserRepsoitory(userRepository);
		userServiceImpl.setPasswordExpReg("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{8,20}$");
	
		assertThrows(ErrorBussinesException.class, () -> userServiceImpl.validatePassword("aaaaaaaaa"));
	}

}
