package cl.genesiscastillo.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.genesiscastillo.entity.Phone;
import cl.genesiscastillo.entity.User;
import cl.genesiscastillo.service.UserService;
import cl.genesiscastillo.vo.PhoneVO;
import cl.genesiscastillo.vo.UserVO;

@WebMvcTest(UserController.class)
class UserControllerTest {

	static final String USER_UUID = "67c26dfb-63ee-4ebc-b869-57bb3194af6e";
	static final String USER_NAME = "cesar castillo";
	static final String USER_EMAIL = "cepija6292@kaftee.com"; 	
	static final String USER_PASSWORD = "6oR9pSPj!Xg1";
	static final String USER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRta";
	static final Collection<Phone> USER_PHONES = Collections.emptyList();
	
	static final String PHONE_NUMBER = "99999999";
	static final String PHONE_CITY_CODE = "01";
	static final String PHONE_COUNTRY_CODE = "56";
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;	
	
	@MockBean
	UserService userServiceMocked;

	@Test
	void testPostUser() throws Exception {
		Collection<PhoneVO> phoneVOs = new ArrayList<PhoneVO>();
		phoneVOs.add(new PhoneVO(PHONE_NUMBER, PHONE_CITY_CODE, PHONE_COUNTRY_CODE));
		UserVO userVO = new UserVO();
		userVO.setEmail(USER_EMAIL);
		userVO.setName(USER_NAME);
		userVO.setPassword(USER_PASSWORD);
		userVO.setPhones( phoneVOs );
		
		User user = new User(USER_NAME, USER_EMAIL, USER_PASSWORD, USER_TOKEN, USER_PHONES);		
		
		given(userServiceMocked.saveUser(userVO)).willReturn(user);
		
		this.mockMvc
			.perform(
				post("/api/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userVO))
			)
			.andExpect(status().isCreated())
			.andDo(print());
	}

	@Test
	void testGetUser() throws Exception {
		User user = new User(USER_NAME, USER_EMAIL, USER_PASSWORD, USER_TOKEN, USER_PHONES);
		
		Optional<User> optional = Optional.of(user); 
		
		given(userServiceMocked.findByEmail("cepija6292@kaftee.com")).willReturn(optional);
		
		this.mockMvc.perform(get("/api/users?email=cepija6292@kaftee.com"))
		.andExpect(status().isOk());
	}

	@Test
	void testGetUserNUll() throws Exception {
		Optional<User> optional = Optional.empty(); 
		
		given(userServiceMocked.findByEmail("cepija6292@kaftee.com")).willReturn(optional);
		
		this.mockMvc.perform(get("/api/users?email=cepija6292@kaftee.com"))
		.andExpect(status().isNoContent());
	}

}
